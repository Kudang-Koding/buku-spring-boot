package kudangkoding.gamifikasi.dto.forms;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

import javax.validation.constraints.NotBlank;

@Getter
@Setter
@Accessors(chain = true)
public class UserHistoryXpAddForm {

    @NotBlank(message = "User_id harus diisi!")
    private String user_id;

    @NotBlank(message = "Type harus diisi!")
    private String type;

    @NotBlank(message = "Date harus diisi!")
    private String date;

    private Integer in_xp;

    private Integer out_xp;

    private String playlist_video_id;

}
