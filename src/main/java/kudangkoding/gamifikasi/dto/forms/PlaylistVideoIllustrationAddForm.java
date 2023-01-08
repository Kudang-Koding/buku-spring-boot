package kudangkoding.gamifikasi.dto.forms;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

@Getter
@Setter
@Accessors(chain = true)
public class PlaylistVideoIllustrationAddForm extends BaseForm{

    private String url;

    private Integer illustration_order;

}
