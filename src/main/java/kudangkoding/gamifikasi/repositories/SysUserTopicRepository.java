package kudangkoding.gamifikasi.repositories;

import kudangkoding.gamifikasi.models.SysUserTopic;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SysUserTopicRepository extends PagingAndSortingRepository<SysUserTopic, String>, QuerydslPredicateExecutor<SysUserTopic> {

    @Query(value = "SELECT * FROM sys_user_topics WHERE user_id =?1 AND topic_id = ?2 LIMIT 1", nativeQuery = true)
    SysUserTopic findByUserTopic(String userId, String TopicId);

}
