package kudangkoding.gamifikasi.dto.mappers;

import kudangkoding.gamifikasi.dto.models.AuthenticationDto;
import kudangkoding.gamifikasi.dto.models.SysUserDto;
import kudangkoding.gamifikasi.models.SysUser;

import java.util.Date;

public class UserMapper {

    public static SysUserDto toDto(SysUser model) {
        SysUserDto sysUserDto = new SysUserDto();
        sysUserDto.setEmail(model.getEmail()).setEmail_verified(model.getEmail_verified()).setImage_url(model.getImage_url())
                .setName(model.getName()).setProvider(model.getProvider()).setProvider_id(model.getProvider_id()).setRole_code(model.getRole_code())
                .setTotal_xp(model.getTotalXp()).setDeleted(model.getDeleted())
                .setId(model.getId()).setCreated_at(model.getCreated_at()).setUpdated_at(model.getUpdated_at());

        if (model.getRole_data() != null) {
            sysUserDto.setRole_name(model.getRole_data().getName());
        }

        return sysUserDto;
    }

    public static AuthenticationDto toAuthenticationDto(SysUser user, String token, Date expiredAt) {
        SysUserDto userDto = toDto(user);
        return new AuthenticationDto().setUser(userDto).setToken(token)
                .setExpired_at(expiredAt);
    }
}
