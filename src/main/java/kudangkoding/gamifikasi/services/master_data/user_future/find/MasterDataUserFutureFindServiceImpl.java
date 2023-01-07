package kudangkoding.gamifikasi.services.master_data.user_future.find;

import com.querydsl.core.QueryResults;
import com.querydsl.jpa.impl.JPAQuery;
import kudangkoding.gamifikasi.dto.enums.DeletedStatusCode;
import kudangkoding.gamifikasi.dto.mappers.UserFutureMapper;
import kudangkoding.gamifikasi.dto.models.SysUserFutureDto;
import kudangkoding.gamifikasi.dto.queryfilters.UserFutureQueryFilter;
import kudangkoding.gamifikasi.models.SysUserFuture;
import kudangkoding.gamifikasi.models.QSysUserFuture;
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
public class MasterDataUserFutureFindServiceImpl implements MasterDataUserFutureFindService {

    private static final Logger LOG = LogManager.getLogger(MasterDataUserFutureFindServiceImpl.class);

    @PersistenceContext
    EntityManager entityManager;

    @Override
    public Page<SysUserFutureDto> call(UserFutureQueryFilter qf) {
        LOG.info("start find user future");

        List<SysUserFutureDto> dtoList = new ArrayList<>();

        QSysUserFuture qSysUserFuture = QSysUserFuture.sysUserFuture;

        JPAQuery<SysUserFuture> query = new JPAQuery<SysUserFuture>(entityManager).from(qSysUserFuture)
                .limit(qf.getLength()).offset(qf.getStart()).where(qSysUserFuture.deleted.eq(DeletedStatusCode.NON_ACTIVE.val()));

        if (StringUtils.hasLength(qf.getId())) {
            query.where(qSysUserFuture.id.eq(qf.getId()));
        }

        if (StringUtils.hasLength(qf.getUser_id())) {
            query.where(qSysUserFuture.user_id.eq(qf.getUser_id()));
        }

        query.orderBy(qSysUserFuture.id.desc());

        QueryResults<SysUserFuture> results = query.fetchResults();

        dtoList = results.getResults().stream()
                .map(UserFutureMapper::toDto)
                .collect(Collectors.toList());

        LOG.info("finish find user future");
        return new CustomPageImpl<>(dtoList, qf.pageable(), results.getTotal());
    }
}
