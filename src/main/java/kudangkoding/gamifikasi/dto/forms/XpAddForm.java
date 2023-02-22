package kudangkoding.gamifikasi.dto.forms;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

@Getter
@Setter
@Accessors(chain = true)
public class XpAddForm {

    private String userId;

    private String date;

    private Integer inXp;

    private Integer outXp;

    private String type;

    private String playlist_video_id;

}
