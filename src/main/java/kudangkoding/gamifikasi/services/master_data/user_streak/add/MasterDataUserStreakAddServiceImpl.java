package kudangkoding.gamifikasi.services.master_data.user_streak.add;

import kudangkoding.gamifikasi.dto.forms.UserStreakAddForm;
import kudangkoding.gamifikasi.exceptions.BusinessException;
import kudangkoding.gamifikasi.models.SysUser;
import kudangkoding.gamifikasi.models.TrxUserStreak;
import kudangkoding.gamifikasi.repositories.SysUserRepository;
import kudangkoding.gamifikasi.repositories.TrxUserStreakRepository;
import kudangkoding.gamifikasi.utils.DateConverterUtil;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
public class MasterDataUserStreakAddServiceImpl  implements MasterDataUserStreakAddService {


    private static final Logger LOG = LogManager.getLogger(MasterDataUserStreakAddServiceImpl.class);

    @Autowired
    TrxUserStreakRepository trxUserStreakRepository;

    @Autowired
    SysUserRepository sysUserRepository;

    @Transactional
    @Override
    public TrxUserStreak call(UserStreakAddForm form) {
        LOG.info("start add trx user streak");

        SysUser sysUser = sysUserRepository.findById(form.getUser_id()).orElse(null);
        if (sysUser == null) {
            LOG.error("User dengan id " + form.getUser_id() + " tidak ditemukan!");
            throw new BusinessException("User tidak ditemukan!");
        }

        // cek di tanggal tersebut untuk user id tersebut apakah sudah ada data nya
        TrxUserStreak userStreak = trxUserStreakRepository.findByUserIdAndDate(form.getUser_id(), form.getDate());
        // kalo belum ada, dibikin dulu object nya
        if (userStreak == null) {
            userStreak = new TrxUserStreak();
            userStreak.setUser_id(form.getUser_id())
                    .setDate(DateConverterUtil.toDateFormat(form.getDate()));
        }

        trxUserStreakRepository.save(userStreak);

        LOG.info("finish add trx user streak");
        return userStreak;
    }
}
