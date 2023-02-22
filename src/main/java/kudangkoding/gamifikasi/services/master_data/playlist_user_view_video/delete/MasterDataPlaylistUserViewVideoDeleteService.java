package kudangkoding.gamifikasi.services.master_data.playlist_user_view_video.delete;

import kudangkoding.gamifikasi.dto.models.TrxPlaylistUserViewVideoDto;
import kudangkoding.gamifikasi.dto.queryfilters.PlaylistUserViewVideoQueryFilter;

public interface MasterDataPlaylistUserViewVideoDeleteService {

    TrxPlaylistUserViewVideoDto call(PlaylistUserViewVideoQueryFilter qf);

}
