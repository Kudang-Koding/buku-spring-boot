package kudangkoding.gamifikasi.models;

import kudangkoding.gamifikasi.models.hooks.BaseHook;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;

import javax.persistence.*;
import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@Accessors(chain = true)
@Entity
@Table(name = "trx_user_history_xp")
@EntityListeners(BaseHook.class)
public class TrxUserHistoryXp extends BaseModel {

    private String user_id;

    private String type;

    @Temporal(TemporalType.DATE)
    private Date date;

    @Column(name = "in_xp")
    private Integer inXp;

    @Column(name = "out_xp")
    private Integer outXp;

    private String playlist_video_id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", insertable = false, updatable = false, nullable = false)
    private SysUser user_data;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "playlist_video_id", insertable = false, updatable = false, nullable = false)
    private SysUser playlist_data;
}
