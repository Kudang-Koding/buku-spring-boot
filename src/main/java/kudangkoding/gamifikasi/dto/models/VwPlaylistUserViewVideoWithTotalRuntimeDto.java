package kudangkoding.gamifikasi.dto.models;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

import java.util.List;

@Getter
@Setter
@Accessors(chain = true)
public class VwPlaylistUserViewVideoWithTotalRuntimeDto {

    private String id;

    private String user_id;

    private String playlist_id;

    private String playlist_video_id;

    private Integer runtime_persecond;

    private Integer total_watch_time;

}
