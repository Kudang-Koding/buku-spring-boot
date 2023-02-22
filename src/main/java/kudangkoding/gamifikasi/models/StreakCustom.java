package kudangkoding.gamifikasi.models;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

@Getter
@Setter
@Accessors(chain = true)
public class StreakCustom {

    private String userId;

    private String date;

}
