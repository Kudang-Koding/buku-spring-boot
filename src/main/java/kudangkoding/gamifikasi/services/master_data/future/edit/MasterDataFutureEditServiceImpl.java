package kudangkoding.gamifikasi.services.master_data.future.edit;

import kudangkoding.gamifikasi.dto.forms.FutureEditForm;
import kudangkoding.gamifikasi.exceptions.BusinessException;
import kudangkoding.gamifikasi.models.MstFuture;
import kudangkoding.gamifikasi.repositories.MstFutureRepository;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MasterDataFutureEditServiceImpl implements MasterDataFutureEditService{

    private static final Logger LOG = LogManager.getLogger(MasterDataFutureEditServiceImpl.class);

    @Autowired
    MstFutureRepository mstFutureRepository;

    @Override
    public MstFuture call(FutureEditForm form) {
        LOG.info("start edit future");

        MstFuture future = mstFutureRepository.findById(form.getId()).orElse(null);
        if (future == null) {
            LOG.error("Future dengan id " + form.getId() + " tidak ditemukan!");
            throw new BusinessException("Future tidak ditemukan!");
        }

        future.setName(form.getName());
        mstFutureRepository.save(future);

        LOG.info("finish edit future");
        return future;
    }
}
