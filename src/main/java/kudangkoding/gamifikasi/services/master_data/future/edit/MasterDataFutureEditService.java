package kudangkoding.gamifikasi.services.master_data.future.edit;

import kudangkoding.gamifikasi.dto.forms.FutureEditForm;
import kudangkoding.gamifikasi.models.MstFuture;

public interface MasterDataFutureEditService {

    MstFuture call(FutureEditForm form);

}
