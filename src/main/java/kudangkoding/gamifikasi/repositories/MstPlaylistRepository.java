package kudangkoding.gamifikasi.repositories;

import kudangkoding.gamifikasi.models.MstPlaylist;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MstPlaylistRepository extends PagingAndSortingRepository<MstPlaylist, String>, QuerydslPredicateExecutor<MstPlaylist> {
    @Query("SELECT p FROM MstPlaylist p WHERE lower(p.name) LIKE lower(CONCAT('%',:query, '%'))")
    List<MstPlaylist> searchPlaylists(String query);

    @Query(nativeQuery = true, value = "select * from mst_playlists order by total_like desc limit 5")
    List<MstPlaylist> findPlaylistByLikeUser();

}
