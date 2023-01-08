package kudangkoding.gamifikasi.services.master_data.playlist.find;

import kudangkoding.gamifikasi.dto.models.MstPlaylistDto;
import kudangkoding.gamifikasi.dto.queryfilters.PlaylistQueryFilter;
import org.springframework.data.domain.Page;

public interface MasterDataPlaylistFindService {

    Page<MstPlaylistDto> call(PlaylistQueryFilter qf);

}
