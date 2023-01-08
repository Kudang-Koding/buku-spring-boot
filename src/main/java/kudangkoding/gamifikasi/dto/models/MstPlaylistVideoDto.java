package kudangkoding.gamifikasi.dto.models;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

import java.util.List;

@Getter
@Setter
@Accessors(chain = true)
public class MstPlaylistVideoDto extends BaseDto{

    private String playlist_id;

    private Integer episode;

    private String title;

    private String runtime;

    private Integer runtime_persecond;

    private Integer xp;

    private String url;

    private List<MstPlaylistVideoIllustrationDto> playlist_video_illustration_list;

}
