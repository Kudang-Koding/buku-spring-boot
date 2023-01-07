package kudangkoding.gamifikasi.services.master_data.user_topic.edit;

import kudangkoding.gamifikasi.dto.forms.UserTopicEditForm;
import kudangkoding.gamifikasi.exceptions.BusinessException;
import kudangkoding.gamifikasi.models.SysUserTopic;
import kudangkoding.gamifikasi.repositories.SysUserTopicRepository;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MasterDataUserTopicEditServiceImpl implements MasterDataUserTopicEditService {

    private static final Logger LOG = LogManager.getLogger(MasterDataUserTopicEditServiceImpl.class);

    @Autowired
    SysUserTopicRepository sysUserTopicRepository;

    @Override
    public SysUserTopic call(UserTopicEditForm form) {
        LOG.info("start edit user topic");

        SysUserTopic userTopic = sysUserTopicRepository.findById(form.getId()).orElse(null);
        if (userTopic == null) {
            LOG.error("User Topic dengan id " + form.getId() + " tidak ditemukan!");
            throw new BusinessException("User Topic tidak ditemukan!");
        }

        userTopic.setTopic_id(form.getTopic_id());
        userTopic.setUser_id(form.getUser_id());
        sysUserTopicRepository.save(userTopic);

        LOG.info("finish edit user topic");
        return userTopic;
    }

}
