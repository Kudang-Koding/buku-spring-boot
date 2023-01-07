package kudangkoding.gamifikasi.services.authentications.signin;

import kudangkoding.gamifikasi.dto.requests.LoginByGoogleRequest;
import kudangkoding.gamifikasi.models.SysUser;

public interface AuthenticationSignInGoogle {

    SysUser call(LoginByGoogleRequest request);
    
}
