package kudangkoding.gamifikasi.services.master_data.user_streak.find;

import kudangkoding.gamifikasi.models.StreakCustom;
import kudangkoding.gamifikasi.models.StreakCustomWithTotal;
import kudangkoding.gamifikasi.models.TrxUserStreak;
import kudangkoding.gamifikasi.repositories.TrxUserStreakRepository;
import kudangkoding.gamifikasi.utils.DateConverterUtil;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

@Service
public class MasterDataUserStreakFindServiceImpl implements MasterDataUserStreakFindService {

    private static final Logger LOG = LogManager.getLogger(MasterDataUserStreakFindServiceImpl.class);

    @Autowired
    TrxUserStreakRepository trxUserStreakRepository;

    @Override
    public StreakCustomWithTotal getStreak(String userId) {
        LOG.info("start get streak by user");

        StreakCustomWithTotal streakCustomWithTotal = new StreakCustomWithTotal()
                .setTotalStreak(0)
                .setData(new ArrayList<StreakCustom>());

        // instance 2 calendar, 1 buat calendar hari ini, 1 lagi buat H-7 dari hari ini
        Calendar nowCalendar = Calendar.getInstance();
        Calendar tempNowCalendar = Calendar.getInstance();
        Calendar min7Calendar = Calendar.getInstance();
        Calendar thisWeekMondayCalendar = Calendar.getInstance();
//        min7Calendar.add(Calendar.DATE,  -8);
        Integer minusDay = 0;
        if (nowCalendar.get(Calendar.DAY_OF_WEEK) == 1){
            minusDay = 7;
        }
        else{
            minusDay = nowCalendar.get(Calendar.DAY_OF_WEEK) - 1;
        }
        min7Calendar.add(Calendar.DATE,  -(minusDay));

        LOG.info(minusDay);
        LOG.info("start: " + DateConverterUtil.toStringFormat(nowCalendar.getTime()) + ", end: " + DateConverterUtil.toStringFormat(min7Calendar.getTime()));

        List<StreakCustom> streakCustomList = new ArrayList<>();
        // loop dari tanggal hari ini, mundur sampe H-7
        for (Date date = nowCalendar.getTime(); min7Calendar.before(nowCalendar); nowCalendar.add(Calendar.DATE, -1), date = nowCalendar.getTime()){
            String dateString = DateConverterUtil.toStringFormat(date);
            LOG.info(dateString);
            TrxUserStreak userStreak = trxUserStreakRepository.findByUserIdAndDate(userId, dateString);
            if (userStreak == null){
                LOG.info(date + "-" + tempNowCalendar.getTime());
                if (!date.equals(tempNowCalendar.getTime())){ // kalo tanggal hari ini, bisa jadi belum melakukan apa apa, jadi tetep dilanjut ambil data H-1
                    break; // kalo bukan hari ini, otomatis streak nya berakhir
                }
            }
            else{
                StreakCustom streakCustom = new StreakCustom()
                        .setUserId(userStreak.getUser_id())
                        .setDate(DateConverterUtil.toStringFormat(userStreak.getDate()));
                streakCustomList.add(streakCustom);
            }
        }

        if (streakCustomList.size() > 0){
            Integer totalStreak = trxUserStreakRepository.getTotalConsecutiveDates(userId, streakCustomList.get(0).getDate());
            if (totalStreak == null){
                totalStreak = 0;
            }

            streakCustomWithTotal.setTotalStreak(totalStreak);
        } else {
            if (thisWeekMondayCalendar.getTime().equals(tempNowCalendar.getTime())) {
                Integer totalStreak = trxUserStreakRepository.getTotalConsecutiveDates(userId, DateConverterUtil.toStringFormat(nowCalendar.getTime()));
                if (totalStreak == null){
                    totalStreak = 0;
                }

                streakCustomWithTotal.setTotalStreak(totalStreak);
            }
        }

        streakCustomWithTotal.setData(streakCustomList);

        LOG.info("finish get streak by user");
        return streakCustomWithTotal;
    }



}
