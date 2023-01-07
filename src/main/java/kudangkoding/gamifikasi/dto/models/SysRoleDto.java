package kudangkoding.gamifikasi.dto.models;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

@Getter
@Setter
@Accessors(chain = true)
public class SysRoleDto extends BaseDto{

    private String name;

    private String code;

    private Boolean deleted;

}
