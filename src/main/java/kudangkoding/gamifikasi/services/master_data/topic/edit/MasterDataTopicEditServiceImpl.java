package kudangkoding.gamifikasi.services.master_data.topic.edit;

import kudangkoding.gamifikasi.dto.forms.TopicEditForm;
import kudangkoding.gamifikasi.exceptions.BusinessException;
import kudangkoding.gamifikasi.models.MstTopic;
import kudangkoding.gamifikasi.repositories.MstTopicRepository;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MasterDataTopicEditServiceImpl implements MasterDataTopicEditService {

    private static final Logger LOG = LogManager.getLogger(MasterDataTopicEditServiceImpl.class);

    @Autowired
    MstTopicRepository mstTopicRepository;

    @Override
    public MstTopic call(TopicEditForm form) {
        LOG.info("start edit topic");

        MstTopic topic = mstTopicRepository.findById(form.getId()).orElse(null);
        if (topic == null) {
            LOG.error("Topic dengan id " + form.getId() + " tidak ditemukan!");
            throw new BusinessException("Topic tidak ditemukan!");
        }

        topic.setName(form.getName());
        topic.setUrl_picture(form.getUrl_picture());
        mstTopicRepository.save(topic);

        LOG.info("finish edit topic");
        return topic;
    }

}
