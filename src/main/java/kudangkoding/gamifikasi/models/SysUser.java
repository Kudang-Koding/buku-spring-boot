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
@Table(name = "sys_users")
@EntityListeners(BaseHook.class)
public class SysUser extends BaseModel{

    @Column(unique = true)
    private String email;

    private Boolean email_verified;

    private String image_url;

    private String name;

    @Column(name = "total_xp")
    private Integer totalXp;

    private String pwd;

    private String provider;

    private String provider_id;

    private String role_code;

    private Boolean deleted;

    private String expired_deleted_account_at;
//    public void methodExecuteBeforeDeletedSave(final BaseModel model) {
//        model.setExpired_deleted_account_at(DateConverterUtil.thirtyDaysTimeMillis());
//    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "role_code", referencedColumnName = "code", insertable = false, updatable = false, nullable = false)
    private SysRole role_data;

}
