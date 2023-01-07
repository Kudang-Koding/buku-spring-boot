package kudangkoding.gamifikasi.repositories;

import kudangkoding.gamifikasi.dto.enums.ERole;
import kudangkoding.gamifikasi.models.SysRole;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface SysRoleRepository extends PagingAndSortingRepository<SysRole, String>, QuerydslPredicateExecutor<SysRole> {
  SysRole findByCode(ERole name);
}

