package kudangkoding.gamifikasi.repositories;

import kudangkoding.gamifikasi.models.TrxPlaylistUserViewVideo;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TrxPlaylistUserViewVideoRepository extends PagingAndSortingRepository<TrxPlaylistUserViewVideo, String>, QuerydslPredicateExecutor<TrxPlaylistUserViewVideo> {

    @Query(nativeQuery = true, value = "select * from trx_playlist_user_view_videos where user_id = ?1 order by created_at desc limit 1")
    TrxPlaylistUserViewVideo findLatestUserVideoWatch(String userId);
    @Modifying(clearAutomatically = true)
    @Query(nativeQuery = true, value = "update trx_playlist_user_view_videos set watch_time = '0' WHERE user_id = ?1 \n" +
            "AND playlist_video_id = ?2")
    void resetTotalWatchTime(String userId, String playlistVideoId);

}
