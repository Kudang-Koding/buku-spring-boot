package kudangkoding.gamifikasi.models;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

@Getter
@Setter
@NoArgsConstructor
@Accessors(chain = true)
@Entity
@Table(name = "vw_playlist_user_view_videos")
public class VwPlaylistUserViewVideoWithTotalRuntime {

    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
    @Id
    private String id;

    private String user_id;

    private String playlist_id;

    private String playlist_video_id;

    private Integer runtime_persecond;

    private Integer total_watch_time;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", insertable = false, updatable = false, nullable = false)
    private SysUser user_data;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "playlist_id", insertable = false, updatable = false, nullable = false)
    private MstPlaylist playlist_data;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "playlist_video_id", insertable = false, updatable = false, nullable = false)
    private MstPlaylistVideo playlist_video_data;

}
