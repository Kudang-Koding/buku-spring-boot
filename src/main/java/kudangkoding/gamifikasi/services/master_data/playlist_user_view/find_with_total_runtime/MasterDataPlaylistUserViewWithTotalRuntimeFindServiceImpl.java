package kudangkoding.gamifikasi.services.master_data.playlist_user_view.find_with_total_runtime;

import com.querydsl.core.QueryResults;
import com.querydsl.jpa.impl.JPAQuery;
import kudangkoding.gamifikasi.dto.mappers.PlaylistUserViewWithTotalRuntimeMapper;
import kudangkoding.gamifikasi.dto.models.VwPlaylistUserViewWithTotalRuntimeDto;
import kudangkoding.gamifikasi.dto.queryfilters.PlaylistUserViewWithTotalRuntimeQueryFilter;
import kudangkoding.gamifikasi.models.VwPlaylistUserViewWithTotalRuntime;
import kudangkoding.gamifikasi.models.QVwPlaylistUserViewWithTotalRuntime;
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
public class MasterDataPlaylistUserViewWithTotalRuntimeFindServiceImpl implements MasterDataPlaylistUserViewWithTotalRuntimeFindService {

    private static final Logger LOG = LogManager.getLogger(MasterDataPlaylistUserViewWithTotalRuntimeFindServiceImpl.class);

    @PersistenceContext
    EntityManager entityManager;

    @Override
    public Page<VwPlaylistUserViewWithTotalRuntimeDto> call(PlaylistUserViewWithTotalRuntimeQueryFilter qf) {
        LOG.info("start find playlist user view with total runtime");

        List<VwPlaylistUserViewWithTotalRuntimeDto> dtoList = new ArrayList<>();

        QVwPlaylistUserViewWithTotalRuntime qVwPlaylistUserViewWithTotalRuntime = QVwPlaylistUserViewWithTotalRuntime.vwPlaylistUserViewWithTotalRuntime;

        JPAQuery<VwPlaylistUserViewWithTotalRuntime> query = new JPAQuery<VwPlaylistUserViewWithTotalRuntime>(entityManager).from(qVwPlaylistUserViewWithTotalRuntime)
                .limit(qf.getLength()).offset(qf.getStart());

        if (StringUtils.hasLength(qf.getUser_id())) {
            query.where(qVwPlaylistUserViewWithTotalRuntime.user_id.eq(qf.getUser_id()));
        }

        if (StringUtils.hasLength(qf.getPlaylist_id())) {
            query.where(qVwPlaylistUserViewWithTotalRuntime.playlist_id.eq(qf.getPlaylist_id()));
        }

        QueryResults<VwPlaylistUserViewWithTotalRuntime> results = query.fetchResults();

        dtoList =  results.getResults().stream()
                        .map(PlaylistUserViewWithTotalRuntimeMapper::toDto)
                                .collect(Collectors.toList());

        LOG.info("finish find playlist user view with total runtime");
        return new CustomPageImpl<>(dtoList, qf.pageable(), results.getTotal());
    }
}
