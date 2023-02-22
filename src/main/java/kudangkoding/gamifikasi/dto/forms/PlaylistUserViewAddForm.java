package kudangkoding.gamifikasi.dto.forms;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Getter
@Setter
@Accessors(chain = true)
public class PlaylistUserViewAddForm {

    @NotBlank(message = "User_id harus diisi!")
    private String user_id;

    @NotBlank(message = "playlist_id harus diisi!")
    private String playlist_id;

    @NotBlank(message = "playlist_video_id harus diisi!")
    private String playlist_video_id;

    @NotNull(message = "watch_time harus diisi!")
    private Integer watch_time;
}
