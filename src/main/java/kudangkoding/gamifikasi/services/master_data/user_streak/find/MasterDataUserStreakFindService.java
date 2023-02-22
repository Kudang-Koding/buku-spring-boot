package kudangkoding.gamifikasi.services.master_data.user_streak.find;

//import bigio.dimulai.models.StreakCustomWithTotal;
import kudangkoding.gamifikasi.models.StreakCustomWithTotal;

public interface MasterDataUserStreakFindService {

    StreakCustomWithTotal getStreak(String userId);

}
