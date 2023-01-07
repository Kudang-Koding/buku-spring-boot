package kudangkoding.gamifikasi.services.master_data.user.edit;

import kudangkoding.gamifikasi.dto.forms.UserEditForm;
import kudangkoding.gamifikasi.models.SysUser;

public interface MasterDataUserEditService {

    SysUser call(UserEditForm from);

}
