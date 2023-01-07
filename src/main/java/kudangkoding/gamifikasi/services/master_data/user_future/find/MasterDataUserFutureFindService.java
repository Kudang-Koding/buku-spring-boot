package kudangkoding.gamifikasi.services.master_data.user_future.find;

import kudangkoding.gamifikasi.dto.models.SysUserFutureDto;
import kudangkoding.gamifikasi.dto.queryfilters.UserFutureQueryFilter;
import org.springframework.data.domain.Page;

public interface MasterDataUserFutureFindService {

    Page<SysUserFutureDto> call(UserFutureQueryFilter qf);

}
