package kudangkoding.gamifikasi.dto.models;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

@Getter
@Setter
@Accessors(chain = true)
public class MstPlaylistVideoIllustrationDto extends BaseDto{

    private String playlist_video_id;

    private String url;

    private Integer illustration_order;

}
