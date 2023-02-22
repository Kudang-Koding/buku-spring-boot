package kudangkoding.gamifikasi.repositories;

import kudangkoding.gamifikasi.models.TrxUserStreak;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TrxUserStreakRepository extends PagingAndSortingRepository<TrxUserStreak, String>, QuerydslPredicateExecutor<TrxUserStreak> {

    @Query(value = "SELECT * FROM trx_user_streaks WHERE user_id = ?1 AND CAST(date AS varchar) = ?2 LIMIT 1", nativeQuery = true)
    TrxUserStreak findByUserIdAndDate(String userId, String date);

    @Query(value = "select consecutiveDates from (\n" +
            "WITH\n" +
            "  dates(date) AS (\n" +
            "    SELECT DISTINCT date\n" +
            "    FROM trx_user_streaks\n" +
            "    WHERE user_id = ?1\n" +
            "  ),\n" +
            "\n" +
            "  groups AS (\n" +
            "    SELECT\n" +
            "      ROW_NUMBER() OVER (ORDER BY date) AS rn,\n" +
            "      (date + INTERVAL '-1 day' * (ROW_NUMBER() OVER (ORDER BY date))) AS grp,\n" +
            "      date\n" +
            "    FROM dates\n" +
            "  )\n" +
            "SELECT\n" +
            "  COUNT(*) AS consecutiveDates,\n" +
            "  MIN(date) AS minDate,\n" +
            "  MAX(date) AS maxDate\n" +
            "FROM groups\n" +
            "GROUP BY grp\n" +
            "ORDER BY 1 DESC, 2 DESC\n" +
            ") x\n" +
            "WHERE CAST(x.maxDate AS varchar) = ?2", nativeQuery = true)
    Integer getTotalConsecutiveDates(String userId, String maxDate);


}
