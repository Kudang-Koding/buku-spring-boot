package kudangkoding.gamifikasi.services.master_data.playlist.find_by_like;

import kudangkoding.gamifikasi.dto.mappers.PlaylistMapper;
import kudangkoding.gamifikasi.dto.models.MstPlaylistDto;
import kudangkoding.gamifikasi.models.MstPlaylist;
import kudangkoding.gamifikasi.repositories.MstPlaylistRepository;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class MasterDataPlaylistFindByLikeServiceImpl implements MasterDataPlaylistFindByLikeService {

    private static final Logger LOG = LogManager.getLogger(MasterDataPlaylistFindByLikeServiceImpl.class);

    @Autowired
    MstPlaylistRepository mstPlaylistRepository;

    @Override
    public List<MstPlaylistDto> call() {
        LOG.info("Start find playlist by popular like");

        List<MstPlaylistDto> dtoList = new ArrayList<>();

        List<MstPlaylist> mstPlaylist = mstPlaylistRepository.findPlaylistByLikeUser();
        if (mstPlaylist != null) {
            dtoList = mstPlaylist.stream()
                    .map(PlaylistMapper::toDto)
                    .collect(Collectors.toList());
        }

        LOG.info("Finish find playlist by popular like");
        return dtoList;
    }

}
