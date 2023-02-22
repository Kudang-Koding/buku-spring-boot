package kudangkoding.gamifikasi.dto.models;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

@Getter
@Setter
@Accessors(chain = true)
public class TrxUserHistoryXpDto extends BaseDto {

    private String user_id;

    private String type;

    private Integer inXp;

    private Integer outXp;

    private String playlist_video_id;

}
