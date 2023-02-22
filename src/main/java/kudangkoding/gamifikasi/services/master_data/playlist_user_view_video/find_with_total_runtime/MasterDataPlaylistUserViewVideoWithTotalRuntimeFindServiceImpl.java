package kudangkoding.gamifikasi.services.master_data.playlist_user_view_video.find_with_total_runtime;

import com.querydsl.core.QueryResults;
import com.querydsl.jpa.impl.JPAQuery;
import kudangkoding.gamifikasi.dto.mappers.PlaylistUserViewVideoWithTotalRuntimeMapper;
import kudangkoding.gamifikasi.dto.models.VwPlaylistUserViewVideoWithTotalRuntimeDto;
import kudangkoding.gamifikasi.dto.queryfilters.PlaylistUserViewVideoWithTotalRuntimeQueryFilter;
import kudangkoding.gamifikasi.models.VwPlaylistUserViewVideoWithTotalRuntime;
import kudangkoding.gamifikasi.models.QVwPlaylistUserViewVideoWithTotalRuntime;
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
public class MasterDataPlaylistUserViewVideoWithTotalRuntimeFindServiceImpl implements  MasterDataPlaylistUserViewVideoWithTotalRuntimeFindService {

    private static final Logger LOG = LogManager.getLogger(MasterDataPlaylistUserViewVideoWithTotalRuntimeFindServiceImpl.class);

    @PersistenceContext
    EntityManager entityManager;

    @Override
    public Page<VwPlaylistUserViewVideoWithTotalRuntimeDto> call(PlaylistUserViewVideoWithTotalRuntimeQueryFilter qf) {
        LOG.info("start find playlist user view video with total runtime");

        List<VwPlaylistUserViewVideoWithTotalRuntimeDto> dtoList = new ArrayList<>();

        QVwPlaylistUserViewVideoWithTotalRuntime qVwPlaylistUserViewVideoWithTotalRuntime = QVwPlaylistUserViewVideoWithTotalRuntime.vwPlaylistUserViewVideoWithTotalRuntime;

        JPAQuery<VwPlaylistUserViewVideoWithTotalRuntime> query = new JPAQuery<VwPlaylistUserViewVideoWithTotalRuntime>(entityManager).from(qVwPlaylistUserViewVideoWithTotalRuntime)
                .limit(qf.getLength()).offset(qf.getStart());

        if (StringUtils.hasLength(qf.getUser_id())) {
            query.where(qVwPlaylistUserViewVideoWithTotalRuntime.user_id.eq(qf.getUser_id()));
        }

        if (StringUtils.hasLength(qf.getPlaylist_id())) {
            query.where(qVwPlaylistUserViewVideoWithTotalRuntime.playlist_id.eq(qf.getPlaylist_id()));
        }

        if (StringUtils.hasLength(qf.getPlaylist_video_id())) {
            query.where(qVwPlaylistUserViewVideoWithTotalRuntime.playlist_video_id.eq(qf.getPlaylist_video_id()));
        }

        QueryResults<VwPlaylistUserViewVideoWithTotalRuntime> results = query.fetchResults();

        dtoList = results.getResults().stream()
                .map(PlaylistUserViewVideoWithTotalRuntimeMapper::toDto)
                .collect(Collectors.toList());

        LOG.info("finish find playlist user view video with total runtime");
        return new CustomPageImpl<>(dtoList, qf.pageable(), results.getTotal());
    }

}
