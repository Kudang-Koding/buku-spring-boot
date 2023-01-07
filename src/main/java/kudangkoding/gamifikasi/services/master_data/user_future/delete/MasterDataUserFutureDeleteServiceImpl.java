package kudangkoding.gamifikasi.services.master_data.user_future.delete;

import kudangkoding.gamifikasi.dto.enums.DeletedStatusCode;
import kudangkoding.gamifikasi.exceptions.BusinessException;
import kudangkoding.gamifikasi.models.SysUserFuture;
import kudangkoding.gamifikasi.repositories.SysUserFutureRepository;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MasterDataUserFutureDeleteServiceImpl implements MasterDataUserFutureDeleteService {

    private static final Logger LOG = LogManager.getLogger(MasterDataUserFutureDeleteServiceImpl.class);

    @Autowired
    SysUserFutureRepository sysUserFutureRepository;

    @Override
    public SysUserFuture call(String id) {
        LOG.info("start delete user future");

        SysUserFuture userFuture = sysUserFutureRepository.findById(id).orElse(null);
        if (userFuture == null) {
            LOG.error("User Future dengan id " + id + " tidak ditemukan!");
            throw new BusinessException("User Future tidak ditemukan!");
        }

        userFuture.setDeleted(DeletedStatusCode.ACTIVE.val());
        sysUserFutureRepository.save(userFuture);

        LOG.info("finish delete user future");
        return userFuture;
    }

}
