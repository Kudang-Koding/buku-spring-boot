package kudangkoding.gamifikasi.services.master_data.user_future.add;

import kudangkoding.gamifikasi.dto.enums.DeletedStatusCode;
import kudangkoding.gamifikasi.dto.forms.UserFutureAddForm;
import kudangkoding.gamifikasi.exceptions.BusinessException;
import kudangkoding.gamifikasi.models.MstFuture;
import kudangkoding.gamifikasi.models.SysUser;
import kudangkoding.gamifikasi.models.SysUserFuture;
import kudangkoding.gamifikasi.repositories.MstFutureRepository;
import kudangkoding.gamifikasi.repositories.SysUserFutureRepository;
import kudangkoding.gamifikasi.repositories.SysUserRepository;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
public class MasterDataUserFutureAddServiceImpl implements MasterDataUserFutureAddService {

    private static final Logger LOG = LogManager.getLogger(MasterDataUserFutureAddServiceImpl.class);

    @Autowired
    SysUserFutureRepository sysUserFutureRepository;

    @Autowired
    SysUserRepository sysUserRepository;

    @Autowired
    MstFutureRepository mstFutureRepository;

    @Transactional
    @Override
    public SysUserFuture call(UserFutureAddForm form) {
        LOG.info("start add sys user future");

        MstFuture mstFuture =  mstFutureRepository.findById(form.getFuture_id()).orElse(null);
        if (mstFuture == null) {
            LOG.error("Future dengan id " + form.getFuture_id() + " tidak ditemukan!");
            throw new BusinessException("Future tidak ditemukan!");
        }

        SysUser sysUser = sysUserRepository.findById(form.getUser_id()).orElse(null);
        if (sysUser == null) {
            LOG.error("User dengan id " + form.getUser_id() + " tidak ditemukan!");
            throw new BusinessException("User tidak ditemukan!");
        }

        // Cek apakah user sudah menambahkan future
        SysUserFuture sysUserFuture = sysUserFutureRepository.findByUserFuture(form.getUser_id(), form.getFuture_id());
        // Kalau belum ada, buatkan
        if (sysUserFuture == null) {
            sysUserFuture =  new SysUserFuture();
            sysUserFuture.setFuture_id(form.getFuture_id())
                    .setUser_id(form.getUser_id())
                    .setDeleted(DeletedStatusCode.NON_ACTIVE.val());
        }

        sysUserFutureRepository.save(sysUserFuture);

        LOG.info("finish add sys user future");
        return sysUserFuture;
    }

}
