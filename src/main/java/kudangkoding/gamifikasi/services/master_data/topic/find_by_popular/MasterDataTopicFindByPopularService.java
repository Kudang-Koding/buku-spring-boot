package kudangkoding.gamifikasi.services.master_data.topic.find_by_popular;

import kudangkoding.gamifikasi.dto.models.VwPopularTopicDto;

import java.util.List;

public interface MasterDataTopicFindByPopularService {

    List<VwPopularTopicDto> call();

}
