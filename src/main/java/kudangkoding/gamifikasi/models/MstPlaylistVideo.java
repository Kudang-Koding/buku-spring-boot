package kudangkoding.gamifikasi.models;

import kudangkoding.gamifikasi.models.hooks.BaseHook;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;

import javax.persistence.*;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@Accessors(chain = true)
@Entity
@Table(name = "mst_playlist_videos")
@EntityListeners(BaseHook.class)
public class MstPlaylistVideo extends BaseModel {

    private String playlist_id;

    private Integer episode;

    private String title;
    
    private String url;

    private String runtime;

    private Integer runtime_persecond;

    private Boolean deleted;

    private Integer xp;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "playlist_id", insertable = false, updatable = false, nullable = false)
    private MstPlaylist playlist_data;

    @OneToMany(mappedBy = "playlist_video_data", fetch = FetchType.LAZY)
    private Set<MstPlaylistVideoIllustration> list_playlist_video_illustration;

}
