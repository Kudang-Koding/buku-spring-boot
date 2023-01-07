package kudangkoding.gamifikasi.dto.forms;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

import javax.validation.constraints.NotBlank;

@Getter
@Setter
@Accessors(chain = true)
public class UserTopicAddForm {

    @NotBlank(message = "User_id harus diisi!")
    private String user_id;

    @NotBlank(message = "topic_id harus diisi!")
    private String topic_id;

}
