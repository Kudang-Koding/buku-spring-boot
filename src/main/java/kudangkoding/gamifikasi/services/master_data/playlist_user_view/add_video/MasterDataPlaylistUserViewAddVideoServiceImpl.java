package kudangkoding.gamifikasi.services.master_data.playlist_user_view.add_video;

import kudangkoding.gamifikasi.dto.enums.UserHistoryXPTypeCode;
import kudangkoding.gamifikasi.dto.forms.PlaylistUserViewAddForm;
import kudangkoding.gamifikasi.dto.forms.UserHistoryXpAddForm;
import kudangkoding.gamifikasi.dto.forms.UserStreakAddForm;
import kudangkoding.gamifikasi.exceptions.BusinessException;
import kudangkoding.gamifikasi.models.*;
import kudangkoding.gamifikasi.repositories.*;
import kudangkoding.gamifikasi.services.master_data.user_history_xp.add.MasterDataUserHistoryXpAddService;
import kudangkoding.gamifikasi.services.master_data.user_streak.add.MasterDataUserStreakAddService;
import kudangkoding.gamifikasi.utils.DateConverterUtil;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Date;

@Service
public class MasterDataPlaylistUserViewAddVideoServiceImpl implements MasterDataPlaylistUserViewAddVideoService {

    private static final Logger LOG = LogManager.getLogger(MasterDataPlaylistUserViewAddVideoServiceImpl.class);

    @Autowired
    TrxPlaylistUserViewRepository trxPlaylistUserViewRepository;

    @Autowired
    TrxPlaylistUserViewVideoRepository trxPlaylistUserViewVideoRepository;

    @Autowired
    MstPlaylistVideoRepository mstPlaylistVideoRepository;

    @Autowired
    MstPlaylistRepository mstPlaylistRepository;

    @Autowired
    SysUserRepository sysuserRepository;

    @Autowired
    MasterDataUserStreakAddService masterDataUserStreakAddService;

    @Autowired
    MasterDataUserHistoryXpAddService masterDataUserHistoryXpAddService;

    @Transactional
    @Override
    public TrxPlaylistUserView call(PlaylistUserViewAddForm form) {
        LOG.info("start add trx playlist user view");

        MstPlaylist playlist = mstPlaylistRepository.findById(form.getPlaylist_id()).orElse(null);
        if (playlist == null) {
            LOG.error("Playlist dengan id " + form.getUser_id() + " tidak ditemukan!");
            throw new BusinessException("Playlist tidak ditemukan!");
        }

        SysUser sysUser = sysuserRepository.findById(form.getUser_id()).orElse(null);
        if (sysUser == null) {
            LOG.error("User dengan id " + form.getUser_id() + " tidak ditemukan!");
            throw new BusinessException("User tidak ditemukan!");
        }

        MstPlaylistVideo mstPlaylistVideo = mstPlaylistVideoRepository.findById(form.getPlaylist_video_id()).orElse(null);
        if (mstPlaylistVideo == null) {
            LOG.error("Video dengan id " + form.getPlaylist_video_id() + " tidak ditemukan!");
            throw new BusinessException("Video tidak ditemukan!");
        }

        TrxPlaylistUserView playlistUserView = new TrxPlaylistUserView()
                .setPlaylist_id(form.getPlaylist_id()).setUser_id(form.getUser_id());

        trxPlaylistUserViewRepository.save(playlistUserView);

        TrxPlaylistUserViewVideo playlistUserViewVideo = new TrxPlaylistUserViewVideo()
                .setUser_id(form.getUser_id()).setPlaylist_id(form.getPlaylist_id()).setPlaylist_video_id(form.getPlaylist_video_id())
                        .setWatch_time(form.getWatch_time());

        trxPlaylistUserViewVideoRepository.save(playlistUserViewVideo);

        UserStreakAddForm userStreakAddForm = new UserStreakAddForm()
                .setUser_id(form.getUser_id()).setDate(
                        DateConverterUtil.toStringFormat(new Date())
                );
        masterDataUserStreakAddService.call(userStreakAddForm);

        UserHistoryXpAddForm userHistoryXpAddForm = new UserHistoryXpAddForm()
                .setUser_id(form.getUser_id()).setDate(DateConverterUtil.toStringFormat(new Date()))
                .setType(UserHistoryXPTypeCode.WATCH_VIDEO.val()).setIn_xp(UserHistoryXPTypeCode.WATCH_VIDEO.xp())
                        .setOut_xp(0).setPlaylist_video_id(form.getPlaylist_video_id());

        masterDataUserHistoryXpAddService.call(userHistoryXpAddForm);

        LOG.info("finish add trx playlist user view");
        return playlistUserView;
    }




}
