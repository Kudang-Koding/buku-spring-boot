package kudangkoding.gamifikasi.models;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

@Getter
@Setter
@NoArgsConstructor
@Accessors(chain = true)
@Entity
@Table(name = "vw_popular_topics")
public class VwPopularTopic {

    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
    @Id
    private String id;

    private Integer total_like;

    private String topic_id;

    private String topic_name;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "topic_id", insertable = false, updatable = false, nullable = false)
    private MstTopic topic_data;


}
