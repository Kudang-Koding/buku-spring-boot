package kudangkoding.gamifikasi.repositories;

import kudangkoding.gamifikasi.models.MstTopic;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MstTopicRepository extends PagingAndSortingRepository<MstTopic, String>, QuerydslPredicateExecutor<MstTopic> {

    @Query("SELECT t FROM MstTopic t WHERE lower(t.name) LIKE lower(CONCAT('%',:query, '%'))")
    List<MstTopic> searchTopics(String query);

}
