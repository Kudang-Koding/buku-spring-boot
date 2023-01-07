package kudangkoding.gamifikasi.services.master_data.topic.add;

import kudangkoding.gamifikasi.dto.enums.DeletedStatusCode;
import kudangkoding.gamifikasi.dto.forms.TopicAddForm;
import kudangkoding.gamifikasi.models.MstTopic;
import kudangkoding.gamifikasi.repositories.MstTopicRepository;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MasterDataTopicAddServiceImpl implements MasterDataTopicAddService {

    private static final Logger LOG = LogManager.getLogger(MasterDataTopicAddServiceImpl.class);

    @Autowired
    MstTopicRepository mstTopicRepository;

    @Override
    public MstTopic call(TopicAddForm form) {
        LOG.info("start add master future");

        MstTopic topic = new MstTopic()
                .setName(form.getName()).setUrl_picture(form.getUrl_picture()).setDeleted(DeletedStatusCode.NON_ACTIVE.val());

        mstTopicRepository.save(topic);

        LOG.info("finish add master topic");
        return topic;
    }

}
