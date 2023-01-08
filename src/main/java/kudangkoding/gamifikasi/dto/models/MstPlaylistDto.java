package kudangkoding.gamifikasi.dto.models;

import io.swagger.models.auth.In;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

import java.util.List;

@Getter
@Setter
@Accessors(chain = true)
public class MstPlaylistDto extends BaseDto{

    private String name;

    private String description;

    private Integer category;

    private String url_picture;

    private String topic_id;

    private String topic_name;

    private Boolean is_all_viewed;

    private Integer total_chapter;

    private Integer total_like;

    private Boolean is_favorite;

    private String total_runtime;

    private String author;

    private List<MstPlaylistVideoDto> playlist_video_list;

}
