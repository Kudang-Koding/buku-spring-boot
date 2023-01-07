package kudangkoding.gamifikasi.services.master_data.user_topic.delete;

import kudangkoding.gamifikasi.dto.enums.DeletedStatusCode;
import kudangkoding.gamifikasi.exceptions.BusinessException;
import kudangkoding.gamifikasi.models.SysUserTopic;
import kudangkoding.gamifikasi.repositories.SysUserTopicRepository;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MasterDataUserTopicDeleteServiceImpl implements MasterDataUserTopicDeleteService {

    private static final Logger LOG = LogManager.getLogger(MasterDataUserTopicDeleteServiceImpl.class);

    @Autowired
    SysUserTopicRepository sysUserTopicRepository;

    @Override
    public SysUserTopic call(String id) {
        LOG.info("start delete user topic");

        SysUserTopic userTopic = sysUserTopicRepository.findById(id).orElse(null);
        if (userTopic == null) {
            LOG.error("User topic dengan id " + id + " tidak ditemukan!");
            throw new BusinessException("User topic tidak ditemukan!");
        }

        userTopic.setDeleted(DeletedStatusCode.ACTIVE.val());
        sysUserTopicRepository.save(userTopic);

        LOG.info("finish delete user topic");
        return userTopic;
    }

}
