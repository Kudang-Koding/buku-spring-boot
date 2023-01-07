package kudangkoding.gamifikasi.dto.models;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

import javax.persistence.Column;

@Getter
@Setter
@Accessors(chain = true)
public class SysUserDto extends BaseDto{

    @Column(unique = true)
    private String email;

    private Boolean email_verified;

    private String image_url;

    private String name;

    private String pwd;

    private String provider;

    private String provider_id;

    private String role_code;

    private String role_name;

    private Boolean deleted;

    private Integer total_xp;

}
