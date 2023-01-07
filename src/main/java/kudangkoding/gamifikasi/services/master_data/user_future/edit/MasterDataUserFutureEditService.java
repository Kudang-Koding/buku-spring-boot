package kudangkoding.gamifikasi.services.master_data.user_future.edit;

import kudangkoding.gamifikasi.dto.forms.UserFutureEditForm;
import kudangkoding.gamifikasi.models.SysUserFuture;

public interface MasterDataUserFutureEditService {

    SysUserFuture call(UserFutureEditForm form);

}
