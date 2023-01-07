package kudangkoding.gamifikasi.services.authentications.signin_with_uid;

import kudangkoding.gamifikasi.dto.forms.AuthenticationSignInWithUidForm;
import kudangkoding.gamifikasi.models.SysUser;

public interface AuthenticationSignInWithUidService {

    SysUser call(AuthenticationSignInWithUidForm form);

}
