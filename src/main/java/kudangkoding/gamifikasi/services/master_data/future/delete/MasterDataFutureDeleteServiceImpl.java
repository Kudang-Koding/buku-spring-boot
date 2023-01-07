package kudangkoding.gamifikasi.services.master_data.future.delete;

import kudangkoding.gamifikasi.dto.enums.DeletedStatusCode;
import kudangkoding.gamifikasi.exceptions.BusinessException;
import kudangkoding.gamifikasi.models.MstFuture;
import kudangkoding.gamifikasi.repositories.MstFutureRepository;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MasterDataFutureDeleteServiceImpl implements MasterDataFutureDeleteService{

    private static final Logger LOG = LogManager.getLogger(MasterDataFutureDeleteServiceImpl.class);

    @Autowired
    MstFutureRepository mstFutureRepository;

    @Override
    public MstFuture call(String id) {
        LOG.info("start delete future");

        MstFuture future = mstFutureRepository.findById(id).orElse(null);
        if (future == null) {
            LOG.error("Future dengan id " + id + " tidak ditemukan!");
            throw new BusinessException("Future tidak ditemukan!");
        }

        future.setDeleted(DeletedStatusCode.ACTIVE.val());
        mstFutureRepository.save(future);

        LOG.info("finish delete future");
        return future;
    }
}
