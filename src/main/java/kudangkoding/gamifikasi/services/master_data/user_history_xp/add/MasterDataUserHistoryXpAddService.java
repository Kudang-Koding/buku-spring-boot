package kudangkoding.gamifikasi.services.master_data.user_history_xp.add;

import kudangkoding.gamifikasi.dto.forms.UserHistoryXpAddForm;
import kudangkoding.gamifikasi.models.TrxUserHistoryXp;

public interface MasterDataUserHistoryXpAddService {

    TrxUserHistoryXp call(UserHistoryXpAddForm form);


}
