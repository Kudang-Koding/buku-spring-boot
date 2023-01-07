package kudangkoding.gamifikasi.services.master_data.future.add;

import kudangkoding.gamifikasi.dto.forms.FutureAddForm;
import kudangkoding.gamifikasi.models.MstFuture;

public interface MasterDataFutureAddService {

    MstFuture call(FutureAddForm form);

}
