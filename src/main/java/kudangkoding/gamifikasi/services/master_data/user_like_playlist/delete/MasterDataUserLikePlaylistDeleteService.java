package kudangkoding.gamifikasi.services.master_data.user_like_playlist.delete;

import kudangkoding.gamifikasi.dto.forms.UserUnlikePlaylistAddForm;
import kudangkoding.gamifikasi.models.TrxUserLikePlaylist;

public interface MasterDataUserLikePlaylistDeleteService {

    TrxUserLikePlaylist call(UserUnlikePlaylistAddForm form);

}
