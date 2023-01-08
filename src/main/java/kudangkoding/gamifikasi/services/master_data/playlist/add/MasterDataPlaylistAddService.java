package kudangkoding.gamifikasi.services.master_data.playlist.add;

import kudangkoding.gamifikasi.dto.forms.PlaylistAddForm;
import kudangkoding.gamifikasi.models.MstPlaylist;

public interface MasterDataPlaylistAddService {

    MstPlaylist call(PlaylistAddForm form);

}
