package kudangkoding.gamifikasi.services.master_data.playlist.edit;

import kudangkoding.gamifikasi.dto.forms.PlaylistEditForm;
import kudangkoding.gamifikasi.models.MstPlaylist;

public interface MasterDataPlaylistEditService {

    MstPlaylist call(PlaylistEditForm form);

}
