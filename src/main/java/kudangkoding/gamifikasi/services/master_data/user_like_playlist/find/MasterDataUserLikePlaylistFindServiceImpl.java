package kudangkoding.gamifikasi.services.master_data.user_like_playlist.find;

import com.querydsl.core.QueryResults;
import com.querydsl.jpa.impl.JPAQuery;
import kudangkoding.gamifikasi.dto.mappers.UserLikePlaylistMapper;
import kudangkoding.gamifikasi.dto.models.TrxUserLikePlaylistDto;
import kudangkoding.gamifikasi.dto.queryfilters.UserLikePlaylistQueryFilter;
import kudangkoding.gamifikasi.models.TrxUserLikePlaylist;
import kudangkoding.gamifikasi.models.QTrxUserLikePlaylist;
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
public class MasterDataUserLikePlaylistFindServiceImpl implements MasterDataUserLikePlaylistFindService {

    private static final Logger LOG = LogManager.getLogger(MasterDataUserLikePlaylistFindServiceImpl.class);

    @PersistenceContext
    EntityManager entityManager;

    @Override
    public Page<TrxUserLikePlaylistDto> call(UserLikePlaylistQueryFilter qf) {
        LOG.info("start find user like playlist");

        List<TrxUserLikePlaylistDto> dtoList = new ArrayList<>();

        QTrxUserLikePlaylist qTrxUserLikePlaylist = QTrxUserLikePlaylist.trxUserLikePlaylist;

        JPAQuery<TrxUserLikePlaylist> query = new JPAQuery<TrxUserLikePlaylist>(entityManager).from(qTrxUserLikePlaylist)
                .limit(qf.getLength()).offset(qf.getStart());

        if (StringUtils.hasLength(qf.getId())){
            query.where(qTrxUserLikePlaylist.id.eq(qf.getId()));
        }

        if (StringUtils.hasLength(qf.getUser_id() )){
            query.where(qTrxUserLikePlaylist.user_id.eq(qf.getUser_id()));
        }

        if (StringUtils.hasLength(qf.getPlaylist_id())){
            query.where(qTrxUserLikePlaylist.playlist_id.eq(qf.getPlaylist_id()));
        }

        query.orderBy(qTrxUserLikePlaylist.id.desc());

        QueryResults<TrxUserLikePlaylist> results = query.fetchResults();

        dtoList = results.getResults().stream()
                .map(UserLikePlaylistMapper::toDto)
                .collect(Collectors.toList());

        LOG.info("finish find user like playlist");
        return new CustomPageImpl<>(dtoList, qf.pageable(), results.getTotal());
    }
}
