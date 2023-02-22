package kudangkoding.gamifikasi.models;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

import java.util.List;

@Getter
@Setter
@Accessors(chain = true)
public class StreakCustomWithTotal {

    private Integer totalStreak = 0;

    private List<StreakCustom> data;

}
