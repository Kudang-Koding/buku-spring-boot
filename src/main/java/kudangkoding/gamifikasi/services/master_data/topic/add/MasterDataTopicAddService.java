package kudangkoding.gamifikasi.services.master_data.topic.add;

import kudangkoding.gamifikasi.dto.forms.TopicAddForm;
import kudangkoding.gamifikasi.models.MstTopic;

public interface MasterDataTopicAddService {

    MstTopic call(TopicAddForm form);

}
