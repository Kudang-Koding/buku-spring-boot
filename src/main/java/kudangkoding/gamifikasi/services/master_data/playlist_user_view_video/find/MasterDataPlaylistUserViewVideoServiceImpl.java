package kudangkoding.gamifikasi.services.master_data.playlist_user_view_video.find;

import kudangkoding.gamifikasi.dto.mappers.PlaylistUserViewVideoMapper;
import kudangkoding.gamifikasi.dto.models.TrxPlaylistUserViewVideoDto;
import kudangkoding.gamifikasi.dto.queryfilters.PlaylistUserViewVideoQueryFilter;
import kudangkoding.gamifikasi.models.TrxPlaylistUserViewVideo;
import kudangkoding.gamifikasi.repositories.TrxPlaylistUserViewVideoRepository;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MasterDataPlaylistUserViewVideoServiceImpl implements MasterDataPlaylistUserViewVideoService {

    private static final Logger LOG = LogManager.getLogger(MasterDataPlaylistUserViewVideoServiceImpl.class);

    @Autowired
    TrxPlaylistUserViewVideoRepository trxPlaylistUserViewVideoRepository;

    @Override
    public TrxPlaylistUserViewVideoDto call(PlaylistUserViewVideoQueryFilter qf) {
        LOG.info("start find playlist by last video user watch");

        TrxPlaylistUserViewVideo trxPlaylistUserViewVideo = trxPlaylistUserViewVideoRepository.findLatestUserVideoWatch(qf.getUser_id());
         if (trxPlaylistUserViewVideo != null) {
             TrxPlaylistUserViewVideoDto trxPlaylistUserViewVideoDto = PlaylistUserViewVideoMapper.toDto(trxPlaylistUserViewVideo);
             return trxPlaylistUserViewVideoDto;
        }

        LOG.info("finish find playlist by last video user watch");
         return null;
    }

}
