package kudangkoding.gamifikasi.services.master_data.user_history_xp.add;

import kudangkoding.gamifikasi.dto.enums.UserHistoryXPTypeCode;
import kudangkoding.gamifikasi.dto.forms.UserHistoryXpAddForm;
import kudangkoding.gamifikasi.exceptions.BusinessException;
import kudangkoding.gamifikasi.models.SysUser;
import kudangkoding.gamifikasi.models.TrxUserHistoryXp;
import kudangkoding.gamifikasi.repositories.SysUserRepository;
import kudangkoding.gamifikasi.repositories.TrxUserHistoryXpRepository;
import kudangkoding.gamifikasi.services.master_data.user_streak.add.MasterDataUserStreakAddServiceImpl;
import kudangkoding.gamifikasi.utils.DateConverterUtil;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.transaction.Transactional;

@Service
public class MasterDataUserHistoryXpAddServiceImpl implements MasterDataUserHistoryXpAddService {

    private static final Logger LOG = LogManager.getLogger(MasterDataUserStreakAddServiceImpl.class);

    @Autowired
    TrxUserHistoryXpRepository trxUserHistoryXpRepository;

    @Autowired
    SysUserRepository sysUserRepository;

    @Transactional
    @Override
    public TrxUserHistoryXp call(UserHistoryXpAddForm form) {
        LOG.info("start add trx user history xp");

        SysUser sysUser = sysUserRepository.findById(form.getUser_id()).orElse(null);
        if (sysUser == null) {
            LOG.error("User dengan id " + form.getUser_id() + " tidak ditemukan!");
            throw new BusinessException("User tidak ditemukan!");
        }

        if (!form.getType().equals(UserHistoryXPTypeCode.STREAK.val()) && !form.getType().equals(UserHistoryXPTypeCode.WATCH_VIDEO.val())) {
            LOG.error("Type tidak ditemukan!");
            throw new BusinessException("Type tidak ditemukan!");
        }

        // cek di tanggal tersebut untuk user id tersebut apakah sudah ada data nya
        TrxUserHistoryXp userHistoryXp = null;
        if (form.getType().equals(UserHistoryXPTypeCode.STREAK.val())) {
            userHistoryXp = trxUserHistoryXpRepository.findByUserIdAndDateAndTypeStreak(form.getUser_id(), form.getDate());

        } else if (form.getType().equals(UserHistoryXPTypeCode.WATCH_VIDEO.val())){
            // Type harus diisi dengan Streak atau Watch video
            if (!StringUtils.hasLength(form.getPlaylist_video_id())) {
                LOG.error("Chapter id harus diisi!");
                throw new BusinessException("Chapter id tidak ditemukan!");
            }
            userHistoryXp = trxUserHistoryXpRepository.findUserIdByPlaylistVideoId(form.getUser_id(), form.getPlaylist_video_id());
        }

        // kalo belum ada, dibikin dulu object nya
        if (userHistoryXp == null) {
            userHistoryXp = new TrxUserHistoryXp();
            userHistoryXp.setUser_id(form.getUser_id())
                    .setType(form.getType())
                    .setDate(DateConverterUtil.toDateFormat(form.getDate()))
                    .setInXp(form.getIn_xp()).setOutXp(form.getOut_xp())
                    .setPlaylist_video_id(form.getPlaylist_video_id());

            updateXpToUser(sysUser, form.getIn_xp(), form.getOut_xp());
        }

        trxUserHistoryXpRepository.save(userHistoryXp);

        LOG.info("finish add trx user history xp");
        return userHistoryXp;
    }

    private void updateXpToUser(SysUser user, Integer inXp, Integer outXp) {
        Integer totalXp = user.getTotalXp() + inXp - outXp;
        user.setTotalXp(totalXp);

        sysUserRepository.save(user);
    }
}
