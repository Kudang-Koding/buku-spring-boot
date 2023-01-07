package kudangkoding.gamifikasi.services.master_data.user_topic.add;

import kudangkoding.gamifikasi.dto.forms.UserTopicAddForm;
import kudangkoding.gamifikasi.models.SysUserTopic;

public interface MasterDataUserTopicAddService {

    SysUserTopic call(UserTopicAddForm form);

}
