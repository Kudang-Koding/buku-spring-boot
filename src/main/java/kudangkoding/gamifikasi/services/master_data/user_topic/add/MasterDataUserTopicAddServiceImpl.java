package kudangkoding.gamifikasi.services.master_data.user_topic.add;

import kudangkoding.gamifikasi.dto.enums.DeletedStatusCode;
import kudangkoding.gamifikasi.dto.forms.UserTopicAddForm;
import kudangkoding.gamifikasi.exceptions.BusinessException;
import kudangkoding.gamifikasi.models.MstTopic;
import kudangkoding.gamifikasi.models.SysUser;
import kudangkoding.gamifikasi.models.SysUserTopic;
import kudangkoding.gamifikasi.repositories.MstTopicRepository;
import kudangkoding.gamifikasi.repositories.SysUserRepository;
import kudangkoding.gamifikasi.repositories.SysUserTopicRepository;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
public class MasterDataUserTopicAddServiceImpl implements MasterDataUserTopicAddService {

    private static final Logger LOG = LogManager.getLogger(MasterDataUserTopicAddServiceImpl.class);

    @Autowired
    SysUserTopicRepository sysUserTopicRepository;

    @Autowired
    SysUserRepository sysUserRepository;

    @Autowired
    MstTopicRepository mstTopicRepository;

    @Transactional
    @Override
    public SysUserTopic call(UserTopicAddForm form) {
        LOG.info("start add sys user topic");

        SysUser sysUser = sysUserRepository.findById(form.getUser_id()).orElse(null);
        if (sysUser == null) {
            LOG.error("User dengan id " + form.getUser_id() + " tidak ditemukan!");
            throw new BusinessException("User tidak ditemukan!");
        }

        MstTopic mstTopic = mstTopicRepository.findById(form.getTopic_id()).orElse(null);
        if (mstTopic == null) {
            LOG.error("Topic dengan id " + form.getTopic_id() + " tidak ditemukan!");
            throw new BusinessException("Topic tidak ditemukan!");
        }

        // Cek apakah user sudah menambahkan topic yang mereka inginkan
        SysUserTopic sysUserTopic = sysUserTopicRepository.findByUserTopic(form.getUser_id(), form.getTopic_id());
        // Kalau belum ada, buatkan
        if (sysUserTopic == null) {
            sysUserTopic = new SysUserTopic();
            sysUserTopic.setTopic_id(form.getTopic_id())
                    .setUser_id(form.getUser_id())
                    .setDeleted(DeletedStatusCode.NON_ACTIVE.val());
        }

        sysUserTopicRepository.save(sysUserTopic);

        LOG.info("finish add sys user topic");
        return sysUserTopic;
    }

}
