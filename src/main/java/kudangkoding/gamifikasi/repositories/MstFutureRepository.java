package kudangkoding.gamifikasi.repositories;

import kudangkoding.gamifikasi.models.MstFuture;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;

public interface MstFutureRepository extends PagingAndSortingRepository<MstFuture, String>, QuerydslPredicateExecutor<MstFuture> {

    @Query("SELECT f FROM MstFuture f WHERE lower(f.name) LIKE lower(CONCAT('%',:query, '%'))")
    List<MstFuture> searchFutures(String query);

}