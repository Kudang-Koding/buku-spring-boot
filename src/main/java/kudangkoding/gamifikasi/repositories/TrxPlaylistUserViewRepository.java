package kudangkoding.gamifikasi.repositories;

import kudangkoding.gamifikasi.models.TrxPlaylistUserView;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TrxPlaylistUserViewRepository extends PagingAndSortingRepository<TrxPlaylistUserView, String>, QuerydslPredicateExecutor<TrxPlaylistUserView> {
}
