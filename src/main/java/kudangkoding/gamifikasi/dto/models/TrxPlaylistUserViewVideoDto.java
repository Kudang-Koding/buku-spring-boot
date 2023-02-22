package kudangkoding.gamifikasi.dto.models;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

@Getter
@Setter
@Accessors(chain = true)
public class TrxPlaylistUserViewVideoDto extends BaseDto {

    private String user_id;

    private String playlist_id;

    private String playlist_video_id;

    private Integer watch_time;

}
