package kudangkoding.gamifikasi.repositories;

import kudangkoding.gamifikasi.models.TrxUserHistoryXp;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TrxUserHistoryXpRepository extends PagingAndSortingRepository<TrxUserHistoryXp, String>, QuerydslPredicateExecutor<TrxUserHistoryXp> {

    @Query(value = "SELECT * FROM trx_user_history_xp WHERE user_id = ?1 AND CAST(date AS varchar) = ?2 AND type = 'STREAK' LIMIT 1", nativeQuery = true)
    TrxUserHistoryXp findByUserIdAndDateAndTypeStreak(String userId, String date);

    @Query(value = "SELECT * FROM trx_user_history_xp WHERE user_id = ?1 AND playlist_video_id = ?2 LIMIT 1", nativeQuery = true)
    TrxUserHistoryXp findUserIdByPlaylistVideoId(String userId, String playlistVideoId);

    @Query(value = "SELECT * FROM trx_user_history_xp WHERE user_id = ?1 AND type = ?2 AND CAST(date as varchar) = ?3 LIMIT 1", nativeQuery = true)
    TrxUserHistoryXp findByUserIdAndTypeAndDate(String userId, String type, String date);

    @Query(value = "SELECT * FROM trx_user_history_xp WHERE user_id = ?1 AND CAST(date as varchar) = ?2 AND playlist_video_id = ?3 LIMIT 1", nativeQuery = true)
    TrxUserHistoryXp findByUserIdAndTypePlaylistAndDateAndVideoId(String userId, String date, String playlistVideoId);

}
