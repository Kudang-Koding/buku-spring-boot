package kudangkoding.gamifikasi.dto.forms;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

import javax.validation.constraints.NotBlank;

@Getter
@Setter
@Accessors(chain = true)
public class UserFutureEditForm extends BaseForm {

    @NotBlank(message = "Id harus diisi!")
    private String id;

    @NotBlank(message = "future id harus diisi!")
    private String future_id;

    @NotBlank(message = "user id harus diisi!")
    private String user_id;

}
