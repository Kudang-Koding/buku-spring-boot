package kudangkoding.gamifikasi.services.master_data.user_like_playlist.add;

import kudangkoding.gamifikasi.dto.forms.UserLikePlaylistAddForm;
import kudangkoding.gamifikasi.models.TrxUserLikePlaylist;

public interface MasterDataUserLikePlaylistAddService {

    TrxUserLikePlaylist call(UserLikePlaylistAddForm form);

}
