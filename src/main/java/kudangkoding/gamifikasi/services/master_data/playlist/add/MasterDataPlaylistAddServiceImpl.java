package kudangkoding.gamifikasi.services.master_data.playlist.add;

import kudangkoding.gamifikasi.dto.enums.DeletedStatusCode;
import kudangkoding.gamifikasi.dto.enums.PlaylistCateogyCode;
import kudangkoding.gamifikasi.dto.forms.PlaylistAddForm;
import kudangkoding.gamifikasi.dto.forms.PlaylistVideoAddForm;
import kudangkoding.gamifikasi.dto.forms.PlaylistVideoIllustrationAddForm;
import kudangkoding.gamifikasi.exceptions.BusinessException;
import kudangkoding.gamifikasi.models.MstPlaylist;
import kudangkoding.gamifikasi.models.MstPlaylistVideo;
import kudangkoding.gamifikasi.models.MstPlaylistVideoIllustration;
import kudangkoding.gamifikasi.repositories.MstPlaylistRepository;
import kudangkoding.gamifikasi.repositories.MstPlaylistVideoIllustrationRepository;
import kudangkoding.gamifikasi.repositories.MstPlaylistVideoRepository;
import kudangkoding.gamifikasi.services.master_data.playlist.find.MasterDataPlaylistFindServiceImpl;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class MasterDataPlaylistAddServiceImpl implements MasterDataPlaylistAddService {

    private static final Logger LOG = LogManager.getLogger(MasterDataPlaylistFindServiceImpl.class);

    @Autowired
    MstPlaylistRepository mstPlaylistRepository;

    @Autowired
    MstPlaylistVideoRepository mstPlaylistVideoRepository;

    @Autowired
    MstPlaylistVideoIllustrationRepository mstPlaylistVideoIllustrationRepository;

    @Transactional
    @Override
    public MstPlaylist call(PlaylistAddForm form) {
        LOG.info("start add playlist");

        if (form.getCategory() != PlaylistCateogyCode.VIDEO.val() && form.getCategory() != PlaylistCateogyCode.ILLUSTRATION.val()) {
            LOG.error("Kategori tidak ditemukan!");
            throw new BusinessException("Kategori tidak ditemukan!");
        }

        MstPlaylist playlist = new MstPlaylist()
                .setName(form.getName()).setDescription(form.getDescription()).setUrl_picture(form.getUrl_picture())
                .setIs_all_viewed(false).setDeleted(DeletedStatusCode.NON_ACTIVE.val()).setCategory(form.getCategory());

        mstPlaylistRepository.save(playlist);

        for(PlaylistVideoAddForm playlistVideoAddForm: form.getPlaylist_video_list()) {
            MstPlaylistVideo mstPlaylistVideo = new MstPlaylistVideo();
            mstPlaylistVideo.setPlaylist_id(playlist.getId()).setEpisode(playlistVideoAddForm.getEpisode()).setTitle(playlistVideoAddForm.getTitle())
                    .setUrl(playlistVideoAddForm.getUrl()).setDeleted(DeletedStatusCode.NON_ACTIVE.val());

            mstPlaylistVideoRepository.save(mstPlaylistVideo);

            if (playlistVideoAddForm.getPlaylist_video_illustration_list() != null) {
                for (PlaylistVideoIllustrationAddForm playlistVideoIllustrationAddForm: playlistVideoAddForm.getPlaylist_video_illustration_list()) {
                    MstPlaylistVideoIllustration playlistVideoIllustration = new MstPlaylistVideoIllustration()
                            .setIllustration_order(playlistVideoIllustrationAddForm.getIllustration_order()).setUrl(playlistVideoIllustrationAddForm.getUrl())
                            .setPlaylist_video_id(mstPlaylistVideo.getId());

                    mstPlaylistVideoIllustrationRepository.save(playlistVideoIllustration);
                }
            }
        }

        LOG.info("finish add playlist");
        return playlist;
    }
}
