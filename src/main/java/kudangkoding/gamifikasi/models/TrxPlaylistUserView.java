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
@Table(name = "trx_playlist_user_views")
@EntityListeners(BaseHook.class)
public class TrxPlaylistUserView extends BaseModel {

    private String user_id;

    private String playlist_id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", insertable = false, updatable = false, nullable = false)
    private SysUser user_data;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "playlist_id", insertable = false, updatable = false, nullable = false)
    private MstPlaylist playlist_data;

}
