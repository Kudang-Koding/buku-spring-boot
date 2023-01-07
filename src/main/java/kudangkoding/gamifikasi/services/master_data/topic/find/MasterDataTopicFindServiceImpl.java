package kudangkoding.gamifikasi.services.master_data.topic.find;

import com.querydsl.core.QueryResults;
import com.querydsl.jpa.impl.JPAQuery;
import kudangkoding.gamifikasi.dto.enums.DeletedStatusCode;
import kudangkoding.gamifikasi.dto.mappers.TopicMapper;
import kudangkoding.gamifikasi.dto.models.MstTopicDto;
import kudangkoding.gamifikasi.dto.queryfilters.TopicQueryFilter;
import kudangkoding.gamifikasi.models.MstTopic;
import kudangkoding.gamifikasi.models.QMstTopic;
import kudangkoding.gamifikasi.utils.CustomPageImpl;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class MasterDataTopicFindServiceImpl implements MasterDataTopicFindService {

    private static final Logger LOG = LogManager.getLogger(MasterDataTopicFindServiceImpl.class);

    @PersistenceContext EntityManager entityManager;

    @Override
    public Page<MstTopicDto> call(TopicQueryFilter qf) {
        LOG.info("start find topic");

        List<MstTopicDto> dtoList = new ArrayList<>();

        QMstTopic qMstTopic = QMstTopic.mstTopic;

        JPAQuery<MstTopic> query = new JPAQuery<MstTopic>(entityManager).from(qMstTopic)
                .limit(qf.getLength()).offset(qf.getStart()).where(qMstTopic.deleted.eq(DeletedStatusCode.NON_ACTIVE.val()));

        if (StringUtils.hasLength(qf.getId())){
            query.where(qMstTopic.id.eq(qf.getId()));
        }

        query.orderBy(qMstTopic.name.desc());

        QueryResults<MstTopic> results = query.fetchResults();

        dtoList = results.getResults().stream()
                .map(TopicMapper::toDto)
                .collect(Collectors.toList());

        LOG.info("finish find topic");
        return new CustomPageImpl<>(dtoList, qf.pageable(), results.getTotal());
    }

}
