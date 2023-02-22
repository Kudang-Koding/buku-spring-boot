package kudangkoding.gamifikasi.dto.mappers;

import kudangkoding.gamifikasi.dto.models.VwPopularTopicDto;
import kudangkoding.gamifikasi.models.VwPopularTopic;

public class PopularTopicMapper {

    public static VwPopularTopicDto toDto(VwPopularTopic model) {
        VwPopularTopicDto popularTopicDto = new VwPopularTopicDto();
        popularTopicDto.setId(model.getId()).setTotal_like(model.getTotal_like()).setTopic_id(model.getTopic_id()).setTopic_name(model.getTopic_name());

        return popularTopicDto;
    }

}
