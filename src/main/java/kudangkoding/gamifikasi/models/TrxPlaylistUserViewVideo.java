package kudangkoding.gamifikasi.models;

import kudangkoding.gamifikasi.models.hooks.BaseHook;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;

import javax.persistence.*;

@Getter
@Setter
@NoArgsConstructor
@Accessors(chain = true)
@Entity
@Table(name = "trx_playlist_user_view_videos")
@EntityListeners(BaseHook.class)
public class TrxPlaylistUserViewVideo extends BaseModel {

    private String user_id;

    private String playlist_id;

    private String playlist_video_id;

    private Integer watch_time;

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
