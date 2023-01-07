package kudangkoding.gamifikasi.models.hooks;

import kudangkoding.gamifikasi.models.BaseModel;
import kudangkoding.gamifikasi.models.SysUser;
import kudangkoding.gamifikasi.utils.DateConverterUtil;

import javax.annotation.PreDestroy;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;

public class BaseHook {

    @PrePersist
    public void methodExecuteBeforeSave(final BaseModel model){
        model.setCreated_at(DateConverterUtil.currentTimeMilis());
        model.setUpdated_at(DateConverterUtil.currentTimeMilis());
    }

    @PreUpdate
    public void methodExecuteBeforeUpdate(final BaseModel model){
        model.setUpdated_at(DateConverterUtil.currentTimeMilis());
    }

    @PreDestroy
    public void methodExecuteBeforeDeletedSave(final SysUser model) {
        model.setExpired_deleted_account_at(DateConverterUtil.thirtyDaysTimeMillis());
    }

}
