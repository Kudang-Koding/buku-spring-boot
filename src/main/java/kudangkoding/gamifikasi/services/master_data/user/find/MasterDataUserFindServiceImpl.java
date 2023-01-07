package kudangkoding.gamifikasi.services.master_data.user.find;

import com.querydsl.core.QueryResults;
import com.querydsl.jpa.impl.JPAQuery;
import kudangkoding.gamifikasi.dto.enums.DeletedStatusCode;
import kudangkoding.gamifikasi.dto.mappers.UserMapper;
import kudangkoding.gamifikasi.dto.models.SysUserDto;
import kudangkoding.gamifikasi.dto.queryfilters.UserQueryFilter;
import kudangkoding.gamifikasi.models.SysUser;
import kudangkoding.gamifikasi.models.QSysUser;
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
public class MasterDataUserFindServiceImpl implements MasterDataUserFindService{

    private static final Logger LOG = LogManager.getLogger(MasterDataUserFindServiceImpl.class);

    @PersistenceContext EntityManager entityManager;

    @Override
    public Page<SysUserDto> call(UserQueryFilter qf) {
        LOG.info("start find user");

        List<SysUserDto> dtoList = new ArrayList<>();

        QSysUser qSysUser = QSysUser.sysUser;

        JPAQuery<SysUser> query = new JPAQuery<SysUser>(entityManager).from(qSysUser)
                        .limit(qf.getLength()).offset(qf.getStart())
                .where(qSysUser.deleted.eq(DeletedStatusCode.NON_ACTIVE.val()));

        if (StringUtils.hasLength(qf.getId())) {
            query.where(qSysUser.id.eq(qf.getId()));
        }

        if (StringUtils.hasLength(qf.getSearch_text())) {
            query.where(qSysUser.name.containsIgnoreCase(qf.getSearch_text()));
        }

        QueryResults<SysUser> results = query.fetchResults();

        dtoList = results.getResults().stream()
                .map(UserMapper::toDto)
                .collect(Collectors.toList());

        LOG.info("finish find user");
        return new CustomPageImpl<>(dtoList, qf.pageable(), results.getTotal());
    }
}
