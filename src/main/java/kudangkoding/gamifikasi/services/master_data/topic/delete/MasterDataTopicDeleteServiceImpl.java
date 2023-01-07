package kudangkoding.gamifikasi.services.master_data.topic.delete;

import kudangkoding.gamifikasi.dto.enums.DeletedStatusCode;
import kudangkoding.gamifikasi.exceptions.BusinessException;
import kudangkoding.gamifikasi.models.MstTopic;
import kudangkoding.gamifikasi.repositories.MstTopicRepository;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MasterDataTopicDeleteServiceImpl implements MasterDataTopicDeleteService {

    private static final Logger LOG = LogManager.getLogger(MasterDataTopicDeleteServiceImpl.class);

    @Autowired
    MstTopicRepository mstTopicRepository;

    @Override
    public MstTopic call(String id) {
        LOG.info("start delete topic");

        MstTopic topic = mstTopicRepository.findById(id).orElse(null);
        if (topic == null) {
            LOG.error("Topic dengan id " + id + " tidak ditemukan!");
            throw new BusinessException("Topic tidak ditemukan!");
        }

        topic.setDeleted(DeletedStatusCode.ACTIVE.val());
        mstTopicRepository.save(topic);

        LOG.info("finish delete topic");
        return topic;

    }
}
