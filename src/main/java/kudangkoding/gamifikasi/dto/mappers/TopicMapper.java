package kudangkoding.gamifikasi.dto.mappers;

import kudangkoding.gamifikasi.dto.models.MstTopicDto;
import kudangkoding.gamifikasi.models.MstTopic;


public class TopicMapper {

    public static MstTopicDto toDto(MstTopic model) {
        MstTopicDto topicDto = new MstTopicDto();
        topicDto.setName(model.getName()).setUrl_picture(model.getUrl_picture()).setId(model.getId()).setCreated_at(model.getCreated_at())
                .setUpdated_at(model.getUpdated_at());

        return topicDto;
    }

}
