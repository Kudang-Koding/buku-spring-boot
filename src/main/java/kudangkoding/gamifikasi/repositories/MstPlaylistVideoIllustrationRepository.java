package kudangkoding.gamifikasi.repositories;

import kudangkoding.gamifikasi.models.MstPlaylistVideoIllustration;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MstPlaylistVideoIllustrationRepository extends PagingAndSortingRepository<MstPlaylistVideoIllustration, String>, QuerydslPredicateExecutor<MstPlaylistVideoIllustration> {
}
