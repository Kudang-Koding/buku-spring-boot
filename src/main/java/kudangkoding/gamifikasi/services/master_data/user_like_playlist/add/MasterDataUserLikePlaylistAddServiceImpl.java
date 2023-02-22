package kudangkoding.gamifikasi.services.master_data.user_like_playlist.add;

import kudangkoding.gamifikasi.dto.forms.UserLikePlaylistAddForm;
import kudangkoding.gamifikasi.exceptions.BusinessException;
import kudangkoding.gamifikasi.models.MstPlaylist;
import kudangkoding.gamifikasi.models.SysUser;
import kudangkoding.gamifikasi.models.TrxUserLikePlaylist;
import kudangkoding.gamifikasi.repositories.MstPlaylistRepository;
import kudangkoding.gamifikasi.repositories.SysUserRepository;
import kudangkoding.gamifikasi.repositories.TrxUserLikePlaylistRepository;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
public class MasterDataUserLikePlaylistAddServiceImpl implements MasterDataUserLikePlaylistAddService {

    private static final Logger LOG = LogManager.getLogger(MasterDataUserLikePlaylistAddServiceImpl.class);

    @Autowired
    TrxUserLikePlaylistRepository trxUserLikePlaylistRepository;

    @Autowired
    MstPlaylistRepository mstPlaylistRepository;

    @Autowired
    SysUserRepository sysUserRepository;

    @Transactional
    @Override
    public TrxUserLikePlaylist call(UserLikePlaylistAddForm form) {
        LOG.info("start add trx user like playlist");

        MstPlaylist playlist = mstPlaylistRepository.findById(form.getPlaylist_id()).orElse(null);
        if (playlist == null) {
            LOG.error("Playlist dengan id " + form.getUser_id() + " tidak ditemukan!");
            throw new BusinessException("Playlist tidak ditemukan!");
        }

        SysUser sysUser = sysUserRepository.findById(form.getUser_id()).orElse(null);
        if (sysUser == null) {
            LOG.error("User dengan id " + form.getUser_id() + " tidak ditemukan!");
            throw new BusinessException("User tidak ditemukan!");
        }

        // cek apakah user telah menyukai playlist tersebut atau blm
        TrxUserLikePlaylist trxUserLikePlaylist = trxUserLikePlaylistRepository.findByUserLikePlaylist(form.getUser_id(), form.getPlaylist_id());
        // Kalo belum ada, buatkan
        if (trxUserLikePlaylist == null) {
            trxUserLikePlaylist = new TrxUserLikePlaylist();
            trxUserLikePlaylist.setUser_id(form.getUser_id())
                    .setPlaylist_id(form.getPlaylist_id());
            updateLikeToPlaylist(playlist);
        }

        trxUserLikePlaylistRepository.save(trxUserLikePlaylist);

        LOG.info("finish add trx user like playlist");
        return trxUserLikePlaylist;
    }

    private void updateLikeToPlaylist(MstPlaylist mstPlaylist) {
        Integer totalLike = mstPlaylist.getTotal_like() + 1 ;
        mstPlaylist.setTotal_like(totalLike);

        mstPlaylistRepository.save(mstPlaylist);
    }
}
