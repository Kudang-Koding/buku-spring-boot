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
@Table(name = "mst_playlist_video_illustrations")
@EntityListeners(BaseHook.class)
public class MstPlaylistVideoIllustration extends BaseModel {

    private String playlist_video_id;

    private String url;

    private Integer illustration_order;

    private Boolean deleted;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "playlist_video_id", insertable = false, updatable = false, nullable = false)
    private MstPlaylistVideo playlist_video_data;

}
