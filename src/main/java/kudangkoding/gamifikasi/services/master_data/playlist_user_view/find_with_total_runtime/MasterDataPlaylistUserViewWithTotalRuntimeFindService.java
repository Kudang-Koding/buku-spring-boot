package kudangkoding.gamifikasi.services.master_data.playlist_user_view.find_with_total_runtime;

import kudangkoding.gamifikasi.dto.models.VwPlaylistUserViewWithTotalRuntimeDto;
import kudangkoding.gamifikasi.dto.queryfilters.PlaylistUserViewWithTotalRuntimeQueryFilter;
import org.springframework.data.domain.Page;

public interface MasterDataPlaylistUserViewWithTotalRuntimeFindService {

    Page<VwPlaylistUserViewWithTotalRuntimeDto> call(PlaylistUserViewWithTotalRuntimeQueryFilter qf);

}
