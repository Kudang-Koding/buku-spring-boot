package kudangkoding.gamifikasi.repositories;

import kudangkoding.gamifikasi.models.SysUser;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SysUserRepository extends PagingAndSortingRepository<SysUser, String>, QuerydslPredicateExecutor<SysUser> {

  // Boolean existsByUsername(String username);

  @Query(nativeQuery = true, value = "SELECT * FROM sys_users WHERE email = ?1 and deleted = false LIMIT 1")
  SysUser findByEmail(String email);

  Boolean existsByEmail(String email);

  @Query(nativeQuery = true, value = "SELECT * FROM sys_users WHERE provider_id = ?1 and deleted = false LIMIT 1")
  SysUser findByProvider_id(String uid);

  @Query(nativeQuery = true, value = "SELECT * FROM sys_users ORDER BY total_xp DESC LIMIT 10")
  List<SysUser> findRankUser();
}