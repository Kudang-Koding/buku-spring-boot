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
@Table(name = "mst_playlists")
@EntityListeners(BaseHook.class)
public class MstPlaylist extends BaseModel{
    
    private String name;
    
    private String description;

    private String url_picture;

    private String topic_id;

    private Integer category;

    private Integer total_chapter;

    private Integer total_like;

    private Boolean is_favorite;

    private String total_runtime;

    private Boolean is_all_viewed;

    private Boolean deleted;

    private String author;
    
    @OneToMany(mappedBy = "playlist_data", fetch = FetchType.LAZY)
    @OrderBy("episode asc")
    private Set<MstPlaylistVideo> list_playlist_video;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "topic_id", insertable = false, updatable = false, nullable = false)
    private MstTopic topic_data;
}
