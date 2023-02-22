package kudangkoding.gamifikasi.dto.models;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

@Getter
@Setter
@Accessors(chain = true)
public class VwPlaylistUserViewWithTotalRuntimeDto {

    private String id;

    private String playlist_id;

    private String user_id;

    private Integer total_runtime_persecond;

    private Integer total_watch_time_video;

}
