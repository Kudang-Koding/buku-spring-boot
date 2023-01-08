package kudangkoding.gamifikasi.dto.queryfilters;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

import java.util.List;

@Getter
@Setter
@Accessors(chain = true)
public class PlaylistQueryFilter extends BaseQueryFilter{

    List<String> list_topics;

    private Integer category;

}
