package kudangkoding.gamifikasi.services.master_data.playlist_user_view.find;

import com.querydsl.core.QueryResults;
import com.querydsl.jpa.impl.JPAQuery;
import kudangkoding.gamifikasi.dto.mappers.PlaylistUserViewMapper;
import kudangkoding.gamifikasi.dto.models.VwPlaylistUserViewDto;
import kudangkoding.gamifikasi.dto.queryfilters.PlaylistUserViewQueryFilter;
import kudangkoding.gamifikasi.models.VwPlaylistUserView;
import kudangkoding.gamifikasi.models.QVwPlaylistUserView;
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
public class MasterDataPlaylistUserViewFindServiceImpl implements MasterDataPlaylistUserViewFindService {

    private static final Logger LOG = LogManager.getLogger(MasterDataPlaylistUserViewFindServiceImpl.class);

    @PersistenceContext
    EntityManager entityManager;

    @Override
    public Page<VwPlaylistUserViewDto> call(PlaylistUserViewQueryFilter qf) {
        LOG.info("start find playlist user view");

        List<VwPlaylistUserViewDto> dtoList = new ArrayList<>();

        QVwPlaylistUserView qVwPlaylistUserView = QVwPlaylistUserView.vwPlaylistUserView;

        JPAQuery<VwPlaylistUserView> query = new JPAQuery<VwPlaylistUserView>(entityManager).from(qVwPlaylistUserView)
                .limit(qf.getLength()).offset(qf.getStart());


        if (StringUtils.hasLength(qf.getId())) {
            query.where(qVwPlaylistUserView.id.eq(qf.getId()));
        }

        if (qf.getId() != null) {
            query.where(qVwPlaylistUserView.id.eq(qf.getId()));
        }

        if (qf.getUser_id() != null) {
            query.where(qVwPlaylistUserView.user_id.eq(qf.getUser_id()));
        }

        if (qf.getPlaylist_id() != null) {
            query.where(qVwPlaylistUserView.playlist_id.eq(qf.getPlaylist_id()));
        }

        if (StringUtils.hasLength(qf.getSearch_text())) {
            query.where(qVwPlaylistUserView.user_id.containsIgnoreCase(qf.getSearch_text())
                    .or(qVwPlaylistUserView.id.containsIgnoreCase(qf.getSearch_text())
                    .or(qVwPlaylistUserView.playlist_id.containsIgnoreCase(qf.getSearch_text())))
            );
        }

        query.orderBy(qVwPlaylistUserView.id.desc());

        QueryResults<VwPlaylistUserView> results = query.fetchResults();

        dtoList = results.getResults().stream()
                .map(PlaylistUserViewMapper::toDto)
                .collect(Collectors.toList());

        LOG.info("finish find playlist user view");
        return new CustomPageImpl<>(dtoList, qf.pageable(), results.getTotal());
    }

}
