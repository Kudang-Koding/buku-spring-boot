package kudangkoding.gamifikasi.services.master_data.user_like_playlist.delete;

import kudangkoding.gamifikasi.dto.forms.UserUnlikePlaylistAddForm;
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
public class MasterDataUserLikePlaylistDeleteServiceImpl implements MasterDataUserLikePlaylistDeleteService {

    private static final Logger LOG = LogManager.getLogger(MasterDataUserLikePlaylistDeleteServiceImpl.class);

    @Autowired
    MstPlaylistRepository mstPlaylistRepository;

    @Autowired
    TrxUserLikePlaylistRepository trxUserLikePlaylistRepository;

    @Autowired
    SysUserRepository sysUserRepository;

    @Transactional
    @Override
    public TrxUserLikePlaylist call(UserUnlikePlaylistAddForm form) {
        LOG.info("start add trx user unlike playlist");

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

        TrxUserLikePlaylist trxUserLikePlaylist = trxUserLikePlaylistRepository.findByUserLikePlaylist(form.getUser_id(), form.getPlaylist_id());
        if (trxUserLikePlaylist != null) {
            trxUserLikePlaylistRepository.deleteById(trxUserLikePlaylist.getId());
            updateLikeToPlaylist(playlist);
        }

        LOG.info("finish delete user like playlist");
        return null;
    }

    private void updateLikeToPlaylist(MstPlaylist mstPlaylist) {
        Integer totalLike = mstPlaylist.getTotal_like() - 1 ;
        mstPlaylist.setTotal_like(totalLike);

        mstPlaylistRepository.save(mstPlaylist);
    }
}
