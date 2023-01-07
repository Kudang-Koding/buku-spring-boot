package kudangkoding.gamifikasi.services.master_data.user_topic.edit;

import kudangkoding.gamifikasi.dto.forms.UserTopicEditForm;
import kudangkoding.gamifikasi.models.SysUserTopic;

public interface MasterDataUserTopicEditService {

    SysUserTopic call(UserTopicEditForm form);

}
