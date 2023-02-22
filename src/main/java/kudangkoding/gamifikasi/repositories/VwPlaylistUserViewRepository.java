package kudangkoding.gamifikasi.repositories;

import kudangkoding.gamifikasi.models.VwPlaylistUserViewWithTotalRuntime;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VwPlaylistUserViewRepository extends PagingAndSortingRepository<VwPlaylistUserViewWithTotalRuntime, String>, QuerydslPredicateExecutor<VwPlaylistUserViewWithTotalRuntime> {
}
