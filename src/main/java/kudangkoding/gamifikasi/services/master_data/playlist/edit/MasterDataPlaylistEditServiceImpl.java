package kudangkoding.gamifikasi.services.master_data.playlist.edit;

import kudangkoding.gamifikasi.dto.enums.PlaylistCateogyCode;
import kudangkoding.gamifikasi.dto.forms.PlaylistEditForm;
import kudangkoding.gamifikasi.exceptions.BusinessException;
import kudangkoding.gamifikasi.models.MstPlaylist;
import kudangkoding.gamifikasi.repositories.MstPlaylistRepository;
import kudangkoding.gamifikasi.repositories.MstPlaylistVideoIllustrationRepository;
import kudangkoding.gamifikasi.repositories.MstPlaylistVideoRepository;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class MasterDataPlaylistEditServiceImpl implements MasterDataPlaylistEditService{

    private static final Logger LOG = LogManager.getLogger(MasterDataPlaylistEditServiceImpl.class);

    @Autowired
    MstPlaylistRepository mstPlaylistRepository;

    @Autowired
    MstPlaylistVideoRepository mstPlaylistVideoRepository;

    @Autowired
    MstPlaylistVideoIllustrationRepository mstPlaylistVideoIllustrationRepository;

    @Transactional
    @Override
    public MstPlaylist call(PlaylistEditForm form) {
        LOG.info("start edit playlist");

        MstPlaylist playlist = mstPlaylistRepository.findById(form.getId()).orElse(null);
        if (playlist == null) {
            LOG.error("Playlist dengan id " + form.getId() + " tidak ditemukan!");
            throw new BusinessException("Playlist tidak ditemukan!");
        }

        if (form.getCategory() != PlaylistCateogyCode.VIDEO.val() && form.getCategory() != PlaylistCateogyCode.ILLUSTRATION.val()) {
            LOG.error("Kategori tidak ditemukan!");
            throw new BusinessException("Kategori tidak ditemukan!");
        }

        playlist.setName(form.getName()).setDescription(form.getDescription()).setUrl_picture(form.getUrl_picture()).setCategory(form.getCategory());

        mstPlaylistRepository.save(playlist);

        LOG.info("finish edit playlist");
        return playlist;
    }
}
