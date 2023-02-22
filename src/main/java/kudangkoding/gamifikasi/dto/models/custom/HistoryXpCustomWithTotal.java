package kudangkoding.gamifikasi.dto.models.custom;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

import java.util.List;

@Getter
@Setter
@Accessors(chain = true)
public class HistoryXpCustomWithTotal {

    private Integer totalXp = 0;

    private List<HistoryXpCustom> data;

}
