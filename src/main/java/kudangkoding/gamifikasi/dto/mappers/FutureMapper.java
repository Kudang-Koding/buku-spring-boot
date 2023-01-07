package kudangkoding.gamifikasi.dto.mappers;

import kudangkoding.gamifikasi.dto.models.MstFutureDto;
import kudangkoding.gamifikasi.models.MstFuture;

public class FutureMapper {

    public static MstFutureDto toDto(MstFuture model) {
        MstFutureDto futureDto = new MstFutureDto();
        futureDto.setName(model.getName()).setId(model.getId()).setCreated_at(model.getCreated_at())
                .setUpdated_at(model.getUpdated_at());

        return futureDto;
    }

}
