package kudangkoding.gamifikasi.repositories;

import kudangkoding.gamifikasi.models.SysUserFuture;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SysUserFutureRepository extends PagingAndSortingRepository<SysUserFuture, String>, QuerydslPredicateExecutor<SysUserFuture> {

    @Query(value = "SELECT * FROM sys_user_futures WHERE user_id =?1 AND future_id = ?2 LIMIT 1", nativeQuery = true)
    SysUserFuture findByUserFuture(String userId, String FutureId);

}
