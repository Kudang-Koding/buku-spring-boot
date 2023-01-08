package kudangkoding.gamifikasi.dto.forms;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.List;

@Getter
@Setter
@Accessors(chain = true)
public class PlaylistVideoAddForm extends BaseForm{

    @NotNull(message = "BAB harus diisi!")
    private Integer episode;

    @NotBlank(message = "Judul harus diisi!")
    private String title;

    @NotBlank(message = "Url harus diisi!")
    private String url;

    private List<PlaylistVideoIllustrationAddForm> playlist_video_illustration_list;
}
