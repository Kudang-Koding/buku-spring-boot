package kudangkoding.gamifikasi.dto.queryfilters;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

@Getter
@Setter
@Accessors(chain = true)
public class UserFutureQueryFilter extends BaseQueryFilter {

    private String user_id;

}
