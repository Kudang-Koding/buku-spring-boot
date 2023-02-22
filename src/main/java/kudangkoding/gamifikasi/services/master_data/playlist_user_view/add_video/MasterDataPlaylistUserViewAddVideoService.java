package kudangkoding.gamifikasi.services.master_data.playlist_user_view.add_video;

import kudangkoding.gamifikasi.dto.forms.PlaylistUserViewAddForm;
import kudangkoding.gamifikasi.models.TrxPlaylistUserView;

public interface MasterDataPlaylistUserViewAddVideoService {

    TrxPlaylistUserView call(PlaylistUserViewAddForm form);

}
