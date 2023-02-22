package kudangkoding.gamifikasi.services.master_data.playlist_user_view_video.find;

import kudangkoding.gamifikasi.dto.models.TrxPlaylistUserViewVideoDto;
import kudangkoding.gamifikasi.dto.queryfilters.PlaylistUserViewVideoQueryFilter;

public interface MasterDataPlaylistUserViewVideoService {


    TrxPlaylistUserViewVideoDto call(PlaylistUserViewVideoQueryFilter qf);


}
