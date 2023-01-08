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
public class PlaylistEditForm extends BaseForm{

    @NotBlank(message = "ID harus diisi!")
    private String id;

    @NotBlank(message = "Nama harus diisi!")
    private String name;

    @NotBlank(message = "Deskripsi harus diisi!")
    private String description;

    @NotBlank(message = "Gambar harus diisi!")
    private String url_picture;

    @NotNull(message = "Kategori harus diisi!")
    private Integer category;

    @NotNull(message = "List video harus diisi!")
    private List<PlaylistVideoAddForm> playlist_video_list;

}
