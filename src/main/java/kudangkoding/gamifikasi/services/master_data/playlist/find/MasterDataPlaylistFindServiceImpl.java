package kudangkoding.gamifikasi.services.master_data.playlist.find;

import com.querydsl.core.QueryResults;
import com.querydsl.jpa.impl.JPAQuery;
import kudangkoding.gamifikasi.dto.enums.DeletedStatusCode;
import kudangkoding.gamifikasi.dto.mappers.PlaylistMapper;
import kudangkoding.gamifikasi.dto.models.MstPlaylistDto;
import kudangkoding.gamifikasi.dto.queryfilters.PlaylistQueryFilter;
import kudangkoding.gamifikasi.models.MstPlaylist;
import kudangkoding.gamifikasi.models.QMstPlaylist;
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
public class MasterDataPlaylistFindServiceImpl implements MasterDataPlaylistFindService{

    private static final Logger LOG = LogManager.getLogger(MasterDataPlaylistFindServiceImpl.class);

    @PersistenceContext EntityManager entityManager;

    @Override
    public Page<MstPlaylistDto> call(PlaylistQueryFilter qf) {
        LOG.info("start find playlist");

        List<MstPlaylistDto> dtoList = new ArrayList<>();

        QMstPlaylist qMstPlaylist = QMstPlaylist.mstPlaylist;

        JPAQuery<MstPlaylist> query = new JPAQuery<MstPlaylist>(entityManager).from(qMstPlaylist)
                        .limit(qf.getLength()).offset(qf.getStart()).where(qMstPlaylist.deleted.eq(DeletedStatusCode.NON_ACTIVE.val()));

        if (StringUtils.hasLength(qf.getId())) {
            query.where(qMstPlaylist.id.eq(qf.getId()));
        }

        if (StringUtils.hasLength(qf.getSearch_text())) {
            query.where(qMstPlaylist.name.containsIgnoreCase(qf.getSearch_text())
                    .or(qMstPlaylist.description.containsIgnoreCase(qf.getSearch_text()))
                    .or(qMstPlaylist.topic_data.name.containsIgnoreCase(qf.getSearch_text()))
            );
        }

        if (qf.getList_topics() != null) {
            query.where(qMstPlaylist.topic_data.name.in(qf.getList_topics()));
        }

        query.orderBy(qMstPlaylist.created_at.desc());

        QueryResults<MstPlaylist> results = query.fetchResults();

        dtoList = results.getResults().stream()
                .map(PlaylistMapper::toDto)
                .collect(Collectors.toList());

        LOG.info("finish find playlist");
        return new CustomPageImpl<>(dtoList, qf.pageable(), results.getTotal());
    }
}
