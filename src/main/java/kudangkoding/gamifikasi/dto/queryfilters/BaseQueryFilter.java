package kudangkoding.gamifikasi.dto.queryfilters;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import kudangkoding.gamifikasi.utils.Constant;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

@Getter
@Setter
@Accessors(chain = true)
@ApiModel
public class BaseQueryFilter {

    @ApiModelProperty(notes = "id", required = false)
    private String id = null;
    @ApiModelProperty(notes = "draw", example = "1")
    private Integer draw = 1;
    @ApiModelProperty(notes = "page", example = "0")
    private Integer page = 0;
    @ApiModelProperty(notes = "length", example = "10")
    private Integer length = Constant.DISPLAY_LENGTH;

    private String search_text = null;

    public Pageable pageable() {
        return PageRequest.of(getPage(), getLength());
    }

    public int getStart() {

        if (getPage() > 0 && getLength() > 0) {
            return (getPage() - 1) * getLength();
        }

        return 0;
    }
}
