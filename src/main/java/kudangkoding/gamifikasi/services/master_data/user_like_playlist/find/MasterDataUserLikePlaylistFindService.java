package kudangkoding.gamifikasi.services.master_data.user_like_playlist.find;

import kudangkoding.gamifikasi.dto.models.TrxUserLikePlaylistDto;
import kudangkoding.gamifikasi.dto.queryfilters.UserLikePlaylistQueryFilter;
import org.springframework.data.domain.Page;

public interface MasterDataUserLikePlaylistFindService {

    Page<TrxUserLikePlaylistDto> call(UserLikePlaylistQueryFilter qf);

}
