package kudangkoding.gamifikasi.services.master_data.future.add;

import kudangkoding.gamifikasi.dto.enums.DeletedStatusCode;
import kudangkoding.gamifikasi.dto.forms.FutureAddForm;
import kudangkoding.gamifikasi.models.MstFuture;
import kudangkoding.gamifikasi.repositories.MstFutureRepository;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MasterDataFutureAddServiceImpl implements MasterDataFutureAddService {

    private static final Logger LOG = LogManager.getLogger(MasterDataFutureAddServiceImpl.class);

    @Autowired
    MstFutureRepository mstFutureRepository;

    @Override
    public MstFuture call(FutureAddForm form) {
        LOG.info("start add master future");

        MstFuture future = new MstFuture()
                .setName(form.getName()).setDeleted(DeletedStatusCode.NON_ACTIVE.val());

        mstFutureRepository.save(future);

        LOG.info("finish add master future");
        return future;
    }
}
