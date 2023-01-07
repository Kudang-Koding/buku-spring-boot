package kudangkoding.gamifikasi.services.master_data.user_future.edit;

import kudangkoding.gamifikasi.dto.forms.UserFutureEditForm;
import kudangkoding.gamifikasi.exceptions.BusinessException;
import kudangkoding.gamifikasi.models.SysUserFuture;
import kudangkoding.gamifikasi.repositories.SysUserFutureRepository;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MasterDataUserFutureEditServiceImpl implements MasterDataUserFutureEditService {

    private static final Logger LOG = LogManager.getLogger(MasterDataUserFutureEditServiceImpl.class);

    @Autowired
    SysUserFutureRepository sysUserFutureRepository;

    @Override
    public SysUserFuture call(UserFutureEditForm form) {
        LOG.info("start edit user future");

        SysUserFuture userFuture = sysUserFutureRepository.findById(form.getId()).orElse(null);
        if (userFuture == null) {
            LOG.error("User Future dengan id " + form.getId() + " tidak ditemukan!");
            throw new BusinessException("User Future tidak ditemukan!");
        }

        userFuture.setFuture_id(form.getFuture_id());
        userFuture.setUser_id(form.getUser_id());
        sysUserFutureRepository.save(userFuture);

        LOG.info("finish edit user future");
        return userFuture;
    }

}
