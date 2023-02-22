package kudangkoding.gamifikasi.services.master_data.user.delete;

import kudangkoding.gamifikasi.dto.enums.DeletedStatusCode;
import kudangkoding.gamifikasi.exceptions.BusinessException;
import kudangkoding.gamifikasi.models.SysUser;
import kudangkoding.gamifikasi.repositories.SysUserRepository;
import kudangkoding.gamifikasi.utils.DateConverterUtil;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MasterDataUserDeleteServiceImpl implements MasterDataUserDeleteService {

    private static final Logger LOG = LogManager.getLogger(MasterDataUserDeleteServiceImpl.class);

    @Autowired
    SysUserRepository sysUserRepository;

    @Override
    public SysUser call(String id) {
        LOG.info("start delete user");

        SysUser user = sysUserRepository.findById(id).orElse(null);
        if (user == null) {
            LOG.error("User dengan id " + id + " tidak ditemukan!");
            throw new BusinessException("User tidak ditemukan!");
        }

        user.setDeleted(DeletedStatusCode.ACTIVE.val());
        user.setExpired_deleted_account_at(DateConverterUtil.thirtyDaysTimeMillis());
        sysUserRepository.save(user);

        LOG.info("finish delete user");
        return user;
    }
}
