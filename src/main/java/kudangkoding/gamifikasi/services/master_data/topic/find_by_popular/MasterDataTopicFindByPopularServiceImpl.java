package kudangkoding.gamifikasi.services.master_data.topic.find_by_popular;

import kudangkoding.gamifikasi.dto.mappers.PopularTopicMapper;
import kudangkoding.gamifikasi.dto.models.VwPopularTopicDto;
import kudangkoding.gamifikasi.models.VwPopularTopic;
import kudangkoding.gamifikasi.repositories.VwPopularTopicRepository;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class MasterDataTopicFindByPopularServiceImpl implements MasterDataTopicFindByPopularService {

    private static final Logger LOG = LogManager.getLogger(MasterDataTopicFindByPopularServiceImpl.class);

    @Autowired
    VwPopularTopicRepository vwPopularTopicRepository;

    @Override
    public List<VwPopularTopicDto> call() {
        LOG.info("Start find topic by popular like");

        List<VwPopularTopicDto> dtoList = new ArrayList<>();

        List<VwPopularTopic> vwPopularTopics = vwPopularTopicRepository.findPopularTopic();
        if (vwPopularTopics != null) {
            dtoList = vwPopularTopics.stream()
                    .map(PopularTopicMapper::toDto)
                    .collect(Collectors.toList());
        }

        LOG.info("finish find topic by popular like");
        return dtoList;
    }

}
