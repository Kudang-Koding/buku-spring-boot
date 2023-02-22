package kudangkoding.gamifikasi.dto.models;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

@Getter
@Setter
@Accessors(chain = true)
public class VwPopularTopicDto {

    private String id;

    private String topic_id;

    private Integer total_like;

    private String topic_name;

}
