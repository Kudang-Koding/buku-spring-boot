package kudangkoding.gamifikasi.services.master_data.playlist_user_view.find;

import kudangkoding.gamifikasi.dto.models.VwPlaylistUserViewDto;
import kudangkoding.gamifikasi.dto.queryfilters.PlaylistUserViewQueryFilter;
import org.springframework.data.domain.Page;

public interface MasterDataPlaylistUserViewFindService {

    Page<VwPlaylistUserViewDto> call(PlaylistUserViewQueryFilter qf);
}
