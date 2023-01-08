package kudangkoding.gamifikasi.repositories;

import kudangkoding.gamifikasi.models.MstPlaylistVideo;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MstPlaylistVideoRepository extends PagingAndSortingRepository<MstPlaylistVideo, String>, QuerydslPredicateExecutor<MstPlaylistVideo> {
}
