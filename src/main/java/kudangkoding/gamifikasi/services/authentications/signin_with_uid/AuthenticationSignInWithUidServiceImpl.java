package kudangkoding.gamifikasi.services.authentications.signin_with_uid;

import kudangkoding.gamifikasi.dto.enums.AuthProvider;
import kudangkoding.gamifikasi.dto.enums.DeletedStatusCode;
import kudangkoding.gamifikasi.dto.enums.ERole;
import kudangkoding.gamifikasi.dto.forms.AuthenticationSignInWithUidForm;
import kudangkoding.gamifikasi.models.SysUser;
import kudangkoding.gamifikasi.repositories.SysUserRepository;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

@Service
public class AuthenticationSignInWithUidServiceImpl implements AuthenticationSignInWithUidService{

    private static final Logger LOG = LogManager.getLogger(AuthenticationSignInWithUidServiceImpl.class);

    @Autowired
    SysUserRepository sysUserRepository;

    @Override
    public SysUser call(AuthenticationSignInWithUidForm form) {
        LOG.info("start auth with uid");

        SysUser user = sysUserRepository.findByProvider_id(form.getUid());
        if (user == null) {
            user = new SysUser()
                    .setEmail(form.getEmail()).setProvider_id(form.getUid()).setProvider(AuthProvider.google.name())
                    .setName(form.getName()).setDeleted(DeletedStatusCode.NON_ACTIVE.val()).setEmail_verified(true)
                    .setRole_code(ERole.ROLE_USER.name()).setTotalXp(0);
        }
        else{
            if (StringUtils.hasLength(form.getEmail())) {
                user.setEmail(form.getEmail());
            }

            if (StringUtils.hasLength(form.getName())) {
                user.setName(form.getName());
            }
        }

        sysUserRepository.save(user);

        LOG.info("finish auth with uid");
        return user;
    }
}
