package kudangkoding.gamifikasi.services.master_data.future.find;

import com.querydsl.core.QueryResults;
import com.querydsl.jpa.impl.JPAQuery;
import kudangkoding.gamifikasi.dto.enums.DeletedStatusCode;
import kudangkoding.gamifikasi.dto.mappers.FutureMapper;
import kudangkoding.gamifikasi.dto.models.MstFutureDto;
import kudangkoding.gamifikasi.dto.queryfilters.FutureQueryFilter;
import kudangkoding.gamifikasi.models.MstFuture;
import kudangkoding.gamifikasi.models.QMstFuture;
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
public class MasterDataFutureFindServiceImpl implements MasterDataFutureFindService{

    private static final Logger LOG = LogManager.getLogger(MasterDataFutureFindServiceImpl.class);

    @PersistenceContext EntityManager entityManager;

    @Override
    public Page<MstFutureDto> call(FutureQueryFilter qf) {
        LOG.info("start find future");

        List<MstFutureDto> dtoList = new ArrayList<>();

        QMstFuture qMstFuture = QMstFuture.mstFuture;

        JPAQuery<MstFuture> query = new JPAQuery<MstFuture>(entityManager).from(qMstFuture)
                .limit(qf.getLength()).offset(qf.getStart()).where(qMstFuture.deleted.eq(DeletedStatusCode.NON_ACTIVE.val()));

        if (StringUtils.hasLength(qf.getId())){
            query.where(qMstFuture.id.eq(qf.getId()));
        }

        query.orderBy(qMstFuture.name.desc());

        QueryResults<MstFuture> results = query.fetchResults();

        dtoList = results.getResults().stream()
                .map(FutureMapper::toDto)
                .collect(Collectors.toList());

        LOG.info("finish find future");
        return new CustomPageImpl<>(dtoList, qf.pageable(), results.getTotal());
    }
}
