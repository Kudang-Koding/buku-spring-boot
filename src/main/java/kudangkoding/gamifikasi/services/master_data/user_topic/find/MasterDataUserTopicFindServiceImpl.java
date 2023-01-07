package kudangkoding.gamifikasi.services.master_data.user_topic.find;

import com.querydsl.core.QueryResults;
import com.querydsl.jpa.impl.JPAQuery;
import kudangkoding.gamifikasi.dto.enums.DeletedStatusCode;
import kudangkoding.gamifikasi.dto.mappers.UserTopicMapper;
import kudangkoding.gamifikasi.dto.models.SysUserTopicDto;
import kudangkoding.gamifikasi.dto.queryfilters.UserTopicQueryFilter;
import kudangkoding.gamifikasi.models.SysUserTopic;
import kudangkoding.gamifikasi.models.QSysUserTopic;
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
public class MasterDataUserTopicFindServiceImpl implements MasterDataUserTopicFindService {

    private static final Logger LOG = LogManager.getLogger(MasterDataUserTopicFindServiceImpl.class);

    @PersistenceContext
    EntityManager entityManager;

    @Override
    public Page<SysUserTopicDto> call(UserTopicQueryFilter qf) {
        LOG.info("start find user topic");

        List<SysUserTopicDto> dtoList = new ArrayList<>();

        QSysUserTopic qSysUserTopic = QSysUserTopic.sysUserTopic;

        JPAQuery<SysUserTopic> query = new JPAQuery<SysUserTopic>(entityManager).from(qSysUserTopic)
                        .limit(qf.getLength()).offset(qf.getStart()).where(qSysUserTopic.deleted.eq(DeletedStatusCode.NON_ACTIVE.val()));

        if (StringUtils.hasLength(qf.getId())) {
            query.where(qSysUserTopic.id.eq(qf.getId()));
        }

        if (StringUtils.hasLength(qf.getUser_id())) {
            query.where(qSysUserTopic.user_id.eq(qf.getUser_id()));
        }

        query.orderBy(qSysUserTopic.id.desc());

        QueryResults<SysUserTopic> results = query.fetchResults();

        dtoList = results.getResults().stream()
                        .map(UserTopicMapper::toDto)
                        .collect(Collectors.toList());

        LOG.info("finish find user topic");
        return new CustomPageImpl<>(dtoList, qf.pageable(), results.getTotal());


    }
}
