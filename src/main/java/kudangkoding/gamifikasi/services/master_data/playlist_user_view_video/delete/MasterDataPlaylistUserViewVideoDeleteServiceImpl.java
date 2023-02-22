package kudangkoding.gamifikasi.services.master_data.playlist_user_view_video.delete;

import kudangkoding.gamifikasi.dto.models.TrxPlaylistUserViewVideoDto;
import kudangkoding.gamifikasi.dto.queryfilters.PlaylistUserViewVideoQueryFilter;
import kudangkoding.gamifikasi.repositories.TrxPlaylistUserViewVideoRepository;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
public class MasterDataPlaylistUserViewVideoDeleteServiceImpl implements MasterDataPlaylistUserViewVideoDeleteService {

    private static final Logger LOG = LogManager.getLogger(MasterDataPlaylistUserViewVideoDeleteServiceImpl.class);

    @Autowired
    TrxPlaylistUserViewVideoRepository trxPlaylistUserViewVideoRepository;

    @Transactional
    @Override
    public TrxPlaylistUserViewVideoDto call(PlaylistUserViewVideoQueryFilter qf) {
        LOG.info("start reset playlist video user watch time");

        trxPlaylistUserViewVideoRepository.resetTotalWatchTime(qf.getUser_id(), qf.getPlaylist_video_id());

        LOG.info("finish reset playlist video user watch time");
        return null;
    }
}
