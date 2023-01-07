package kudangkoding.gamifikasi.services.master_data.future.find;

import kudangkoding.gamifikasi.dto.models.MstFutureDto;
import kudangkoding.gamifikasi.dto.queryfilters.FutureQueryFilter;
import org.springframework.data.domain.Page;

public interface MasterDataFutureFindService {

    Page<MstFutureDto> call(FutureQueryFilter qf);

}
