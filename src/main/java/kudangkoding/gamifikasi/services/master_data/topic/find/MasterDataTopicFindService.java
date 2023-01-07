package kudangkoding.gamifikasi.services.master_data.topic.find;

import kudangkoding.gamifikasi.dto.models.MstTopicDto;
import kudangkoding.gamifikasi.dto.queryfilters.TopicQueryFilter;
import org.springframework.data.domain.Page;

public interface MasterDataTopicFindService {

    Page<MstTopicDto> call(TopicQueryFilter qf);

}
