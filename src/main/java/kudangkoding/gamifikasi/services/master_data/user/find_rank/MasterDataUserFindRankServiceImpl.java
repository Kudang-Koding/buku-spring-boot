package kudangkoding.gamifikasi.services.master_data.user.find_rank;

import kudangkoding.gamifikasi.dto.mappers.UserMapper;
import kudangkoding.gamifikasi.dto.models.SysUserDto;
import kudangkoding.gamifikasi.models.SysUser;
import kudangkoding.gamifikasi.repositories.SysUserRepository;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class MasterDataUserFindRankServiceImpl implements MasterDataUserFindRankService {

    private static final Logger LOG = LogManager.getLogger(MasterDataUserFindRankServiceImpl.class);

    @Autowired
    SysUserRepository sysUserRepository;

    @Override
    public List<SysUserDto> call() {
        LOG.info("Start find global rank user");

        List<SysUserDto> dtoList = new ArrayList<>();

        List<SysUser> sysUsers = sysUserRepository.findRankUser();
        if (sysUsers != null) {
            dtoList = sysUsers.stream()
                    .map(UserMapper::toDto)
                    .collect(Collectors.toList());
        }

        LOG.info("Start find global rank user");
        return dtoList;
    }

}
