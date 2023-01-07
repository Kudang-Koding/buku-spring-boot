package kudangkoding.gamifikasi.services.authentications.signin;

import com.google.api.client.googleapis.auth.oauth2.GoogleIdToken;
import com.google.api.client.googleapis.auth.oauth2.GoogleIdToken.Payload;
import com.google.api.client.googleapis.auth.oauth2.GoogleIdTokenVerifier;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.gson.GsonFactory;
import kudangkoding.gamifikasi.dto.enums.AuthProvider;
import kudangkoding.gamifikasi.dto.enums.DeletedStatusCode;
import kudangkoding.gamifikasi.dto.enums.ERole;
import kudangkoding.gamifikasi.dto.requests.LoginByGoogleRequest;
import kudangkoding.gamifikasi.exceptions.BusinessException;
import kudangkoding.gamifikasi.models.SysUser;
import kudangkoding.gamifikasi.repositories.SysUserRepository;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.security.GeneralSecurityException;
import java.util.Arrays;

@Service
public class AuthenticationSignInGoogleImpl implements AuthenticationSignInGoogle{

    private static final Logger LOG = LogManager.getLogger(AuthenticationSignInGoogleImpl.class);

    @Autowired
    SysUserRepository sysUserRepository;

    @Override
    public SysUser call(LoginByGoogleRequest form) {
        GoogleIdTokenVerifier verifier = new GoogleIdTokenVerifier.Builder(new NetHttpTransport(), new GsonFactory())
                .setAudience(Arrays.asList("510190680305-07qc162450vqdm09iu957topieu5nm0p.apps.googleusercontent.com", "510190680305-hejo7g8bteo5co5fo5ndk1vmimdkunja.apps.googleusercontent.com"))
                .build();

        GoogleIdToken idToken = null;
        try {
            idToken = verifier.verify(form.getIdToken());
        } catch (GeneralSecurityException e) {
            LOG.error(e.getMessage());
        } catch (IOException e) {
            LOG.error(e.getMessage());
        }

        if (idToken != null) {
            Payload payload = idToken.getPayload();
            String email = payload.getEmail();
            String name = (String) payload.get("name");
            SysUser userExist = sysUserRepository.findByEmail(email);

            if (userExist == null) {
                SysUser newUser = new SysUser();
                newUser.setName(name)
                        .setEmail(email)
                        .setDeleted(DeletedStatusCode.NON_ACTIVE.val())
                        .setRole_code(ERole.ROLE_USER.name())
                        .setProvider(AuthProvider.google.name())
                        .setProvider_id(form.getIdToken());

                sysUserRepository.save(newUser);
                return newUser;
            }

            return userExist;
        } else {
            LOG.info("Token Id tidak boleh kosong/Token id tidak valid");
            throw new BusinessException("Token Id tidak boleh kosong/Token id tidak valid|Id Token required/Token id not valid");
        }
    }
}
