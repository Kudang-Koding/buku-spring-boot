package kudangkoding.gamifikasi.repositories;

import kudangkoding.gamifikasi.models.VwPopularTopic;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface VwPopularTopicRepository extends PagingAndSortingRepository<VwPopularTopic, String>, QuerydslPredicateExecutor<VwPopularTopic> {

    @Query(nativeQuery = true, value = "select * from vw_popular_topics order by total_like desc limit 5")
    List<VwPopularTopic> findPopularTopic();

}
