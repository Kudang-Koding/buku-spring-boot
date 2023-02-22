package kudangkoding.gamifikasi.repositories;

import kudangkoding.gamifikasi.models.TrxUserLikePlaylist;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TrxUserLikePlaylistRepository extends PagingAndSortingRepository<TrxUserLikePlaylist, String>, QuerydslPredicateExecutor<TrxUserLikePlaylist> {

    @Query(value = "SELECT * FROM trx_users_like_playlists WHERE user_id =?1 AND playlist_id = ?2 LIMIT 1", nativeQuery = true)
    TrxUserLikePlaylist findByUserLikePlaylist(String userId, String PlaylistId);

}
