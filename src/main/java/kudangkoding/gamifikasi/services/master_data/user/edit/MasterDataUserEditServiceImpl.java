package kudangkoding.gamifikasi.services.master_data.user.edit;

import kudangkoding.gamifikasi.dto.forms.UserEditForm;
import kudangkoding.gamifikasi.exceptions.BusinessException;
import kudangkoding.gamifikasi.models.SysUser;
import kudangkoding.gamifikasi.repositories.SysUserRepository;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MasterDataUserEditServiceImpl implements MasterDataUserEditService {

    private static final Logger LOG = LogManager.getLogger(MasterDataUserEditServiceImpl.class);

    @Autowired
    SysUserRepository sysUserRepository;

    @Override
    public SysUser call(UserEditForm form) {
        LOG.info("start edit user");

        SysUser user = sysUserRepository.findById(form.getId()).orElse(null);
        if (user == null) {
            LOG.error("User dengan id " + form.getId() + " tidak ditemukan!");
            throw new BusinessException("User tidak ditemukan!");
        }

        user.setName(form.getName());
        user.setImage_url(form.getImage_url());
        sysUserRepository.save(user);

        LOG.info("finish edit user");
        return user;
    }
}
