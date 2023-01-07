package kudangkoding.gamifikasi.services.master_data.user.find;

import kudangkoding.gamifikasi.dto.models.SysUserDto;
import kudangkoding.gamifikasi.dto.queryfilters.UserQueryFilter;
import org.springframework.data.domain.Page;

public interface MasterDataUserFindService {

    Page<SysUserDto> call(UserQueryFilter qf);

}
