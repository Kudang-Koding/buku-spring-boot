package kudangkoding.gamifikasi.services.master_data.playlist.delete;

import kudangkoding.gamifikasi.dto.enums.DeletedStatusCode;
import kudangkoding.gamifikasi.exceptions.BusinessException;
import kudangkoding.gamifikasi.models.MstPlaylist;
import kudangkoding.gamifikasi.repositories.MstPlaylistRepository;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MasterDataPlaylistDeleteServiceImpl implements MasterDataPlaylistDeleteService{

    private static final Logger LOG = LogManager.getLogger(MasterDataPlaylistDeleteServiceImpl.class);

    @Autowired
    MstPlaylistRepository mstPlaylistRepository;

    @Override
    public MstPlaylist call(String id) {
        LOG.info("start delete playlist");

        MstPlaylist playlist = mstPlaylistRepository.findById(id).orElse(null);
        if (playlist == null) {
            LOG.error("Playlist dengan id " + id + " tidak ditemukan!");
            throw new BusinessException("Playlist tidak ditemukan!");
        }

        playlist.setDeleted(DeletedStatusCode.ACTIVE.val());
        mstPlaylistRepository.save(playlist);

        LOG.info("finish delete playlist");
        return playlist;
    }
}
