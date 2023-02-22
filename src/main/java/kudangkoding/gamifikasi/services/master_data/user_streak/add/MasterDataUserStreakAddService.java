package kudangkoding.gamifikasi.services.master_data.user_streak.add;

import kudangkoding.gamifikasi.dto.forms.UserStreakAddForm;
import kudangkoding.gamifikasi.models.TrxUserStreak;

public interface MasterDataUserStreakAddService {

    TrxUserStreak call(UserStreakAddForm form);

}
