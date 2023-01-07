package kudangkoding.gamifikasi.dto.models;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

import java.util.Date;

@Getter
@Setter
@Accessors(chain = true)
public class AuthenticationDto {

    private SysUserDto user;

    private String token;

    private Date expired_at;

}
