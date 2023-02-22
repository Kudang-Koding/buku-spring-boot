package kudangkoding.gamifikasi.dto.models;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

@Getter
@Setter
@Accessors(chain = true)
public class TrxUserLikePlaylistDto extends BaseDto {

    private String user_id;

    private String playlist_id;

}
