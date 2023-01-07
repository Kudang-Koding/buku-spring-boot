package kudangkoding.gamifikasi.services.master_data.user_detail;

import kudangkoding.gamifikasi.exceptions.ResourceNotFoundException;
import kudangkoding.gamifikasi.models.SysUser;
import kudangkoding.gamifikasi.repositories.SysUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    SysUserRepository sysUserRepository;

    @Override
    @Transactional
    public UserDetails loadUserByUsername(String email)
            throws UsernameNotFoundException {
        SysUser user = sysUserRepository.findByEmail(email);

        return UserDetailsImpl.create(user);
    }

    public SysUser save(SysUser user) {
        return sysUserRepository.save(user);
    }

    public SysUser findOne(String id) {
        SysUser user = sysUserRepository.findById(id).orElse(null);

        return user;
    }

    @Transactional
    public UserDetails loadUserById(String id) {
        SysUser user = sysUserRepository.findById(id).orElse(null);
        if (user == null) {
            throw new ResourceNotFoundException("User", "id", id);
        }

        return UserDetailsImpl.create(user);
    }

    @Transactional
    public UserDetails loadUserByUId(String uId) {
        SysUser user = sysUserRepository.findByProvider_id(uId);
        if (user == null) {
            return null;
        }

        return UserDetailsImpl.create(user);
    }
}
