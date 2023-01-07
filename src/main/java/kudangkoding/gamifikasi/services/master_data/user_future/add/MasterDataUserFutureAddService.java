package kudangkoding.gamifikasi.services.master_data.user_future.add;

import kudangkoding.gamifikasi.dto.forms.UserFutureAddForm;
import kudangkoding.gamifikasi.models.SysUserFuture;

public interface MasterDataUserFutureAddService {

    SysUserFuture call(UserFutureAddForm form);

}
