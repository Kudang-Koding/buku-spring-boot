package kudangkoding.gamifikasi.dto.mappers;

import kudangkoding.gamifikasi.dto.models.TrxUserHistoryXpDto;
import kudangkoding.gamifikasi.models.TrxUserHistoryXp;

public class UserHistoryXpMapper {

    public static TrxUserHistoryXpDto toDto(TrxUserHistoryXp model) {
        TrxUserHistoryXpDto userHistoryXpDto = new TrxUserHistoryXpDto();
        userHistoryXpDto.setUser_id(model.getUser_id()).setInXp(model.getInXp()).setOutXp(model.getOutXp()).setId(model.getId())
                .setCreated_at(model.getCreated_at()).setUpdated_at(model.getUpdated_at());

        return userHistoryXpDto;
    }

}
