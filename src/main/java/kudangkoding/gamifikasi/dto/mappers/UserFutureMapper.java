package kudangkoding.gamifikasi.dto.mappers;

import kudangkoding.gamifikasi.dto.models.SysUserFutureDto;
import kudangkoding.gamifikasi.models.SysUserFuture;

public class UserFutureMapper {

    public static SysUserFutureDto toDto(SysUserFuture model) {
        SysUserFutureDto userFutureDto = new SysUserFutureDto();
        userFutureDto.setFuture_id(model.getFuture_id()).setUser_id(model.getUser_id()).setId(model.getId()).setCreated_at(model.getCreated_at())
                .setUpdated_at(model.getUpdated_at());

        return userFutureDto;
    }

}
