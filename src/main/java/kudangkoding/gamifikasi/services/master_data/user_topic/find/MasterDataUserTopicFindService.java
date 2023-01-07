package kudangkoding.gamifikasi.services.master_data.user_topic.find;

import kudangkoding.gamifikasi.dto.models.SysUserTopicDto;
import kudangkoding.gamifikasi.dto.queryfilters.UserTopicQueryFilter;
import org.springframework.data.domain.Page;

public interface MasterDataUserTopicFindService {

    Page<SysUserTopicDto> call(UserTopicQueryFilter qf);

}
