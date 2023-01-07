package kudangkoding.gamifikasi.dto.forms;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

import javax.validation.constraints.NotBlank;

@Getter
@Setter
@Accessors(chain = true)
public class FutureEditForm extends BaseForm{

    @NotBlank(message = "Id harus diisi!")
    private String id;

    @NotBlank(message = "Nama harus diisi!")
    private String name;
}
