package kudangkoding.gamifikasi.services.master_data.user.activate_user;

import kudangkoding.gamifikasi.dto.enums.DeletedStatusCode;
import kudangkoding.gamifikasi.exceptions.BusinessException;
import kudangkoding.gamifikasi.models.SysUser;
import kudangkoding.gamifikasi.repositories.SysUserRepository;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MasterDataUserActivateUserServiceImpl implements MasterDataUserActivateUserService {

    private static final Logger LOG = LogManager.getLogger(MasterDataUserActivateUserServiceImpl.class);

    @Autowired
    SysUserRepository sysUserRepository;

    @Override
    public SysUser call(String id) {
        LOG.info("start activate user");

        SysUser user = sysUserRepository.findById(id).orElse(null);
        if (user == null) {
            LOG.error("User dengan id " + id + " tidak ditemukan!");
            throw new BusinessException("User tidak ditemukan!");
        }

        user.setDeleted(DeletedStatusCode.NON_ACTIVE.val());
        user.setExpired_deleted_account_at(null);
        sysUserRepository.save(user);

        LOG.info("finish activate user");
        return user;
    }

}
