package kudangkoding.gamifikasi.dto.mappers;

import kudangkoding.gamifikasi.dto.models.TrxUserStreakDto;
import kudangkoding.gamifikasi.models.TrxUserStreak;

public class UserStreakMapper {

    public static TrxUserStreakDto toDto(TrxUserStreak model) {
        TrxUserStreakDto trxUserStreakDto = new TrxUserStreakDto();
        trxUserStreakDto.setUser_id(model.getUser_id()).setId(model.getId()).setCreated_at(model.getCreated_at())
                .setUpdated_at(model.getUpdated_at());

        return trxUserStreakDto;
    }

}
