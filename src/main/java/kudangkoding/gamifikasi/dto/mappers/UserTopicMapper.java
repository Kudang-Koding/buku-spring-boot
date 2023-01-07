package kudangkoding.gamifikasi.dto.mappers;

import kudangkoding.gamifikasi.dto.models.SysUserTopicDto;
import kudangkoding.gamifikasi.models.SysUserTopic;

public class UserTopicMapper {

    public static SysUserTopicDto toDto(SysUserTopic model) {
        SysUserTopicDto userTopicDto = new SysUserTopicDto();
        userTopicDto.setTopic_id(model.getTopic_id()).setUser_id(model.getUser_id()).setId(model.getId()).setCreated_at(model.getCreated_at())
                .setUpdated_at(model.getUpdated_at());

        return userTopicDto;
    }

}
