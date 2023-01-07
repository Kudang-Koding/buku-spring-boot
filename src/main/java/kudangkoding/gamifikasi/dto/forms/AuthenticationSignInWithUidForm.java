package kudangkoding.gamifikasi.dto.forms;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

import javax.validation.constraints.NotBlank;

@Getter
@Setter
@Accessors(chain = true)
public class AuthenticationSignInWithUidForm extends BaseForm{

    @NotBlank(message = "Uid harus diisi!")
    private String uid;

    private String email;

    private String name;

}
