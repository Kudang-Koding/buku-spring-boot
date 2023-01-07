package kudangkoding.gamifikasi.services.master_data.topic.edit;

import kudangkoding.gamifikasi.dto.forms.TopicEditForm;
import kudangkoding.gamifikasi.models.MstTopic;

public interface MasterDataTopicEditService {

    MstTopic call(TopicEditForm form);

}
