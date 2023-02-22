package kudangkoding.gamifikasi.dto.queryfilters;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

@Getter
@Setter
@Accessors(chain = true)
public class PlaylistUserViewWithTotalRuntimeQueryFilter extends BaseQueryFilter {

    private String playlist_id;

    private String user_id;

}
