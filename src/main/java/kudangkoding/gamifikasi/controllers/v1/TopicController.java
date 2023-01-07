package kudangkoding.gamifikasi.controllers.v1;

import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import kudangkoding.gamifikasi.dto.forms.TopicAddForm;
import kudangkoding.gamifikasi.dto.forms.TopicEditForm;
import kudangkoding.gamifikasi.dto.mappers.TopicMapper;
import kudangkoding.gamifikasi.dto.models.MstTopicDto;
import kudangkoding.gamifikasi.dto.queryfilters.TopicQueryFilter;
import kudangkoding.gamifikasi.dto.responses.DatatableResponse;
import kudangkoding.gamifikasi.dto.responses.Response;
import kudangkoding.gamifikasi.exceptions.BusinessException;
import kudangkoding.gamifikasi.models.MstTopic;
import kudangkoding.gamifikasi.services.master_data.topic.add.MasterDataTopicAddService;
import kudangkoding.gamifikasi.services.master_data.topic.delete.MasterDataTopicDeleteService;
import kudangkoding.gamifikasi.services.master_data.topic.edit.MasterDataTopicEditService;
import kudangkoding.gamifikasi.services.master_data.topic.find.MasterDataTopicFindService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;


@RestController
@RequestMapping("/v1/topic")
public class TopicController {

    private static final Logger LOG = LogManager.getLogger(TopicController.class);

    @Autowired private MasterDataTopicAddService masterDataTopicAddService;

    @Autowired private MasterDataTopicEditService masterDataTopicEditService;

    @Autowired private MasterDataTopicFindService masterDataTopicFindService;

    @Autowired private MasterDataTopicDeleteService masterDataTopicDeleteService;


    @GetMapping
    @ApiOperation(value = "API get topic", response = DatatableResponse.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully"),
            @ApiResponse(code = 500, message = "Internal Server Error")
    })
//    @ApiImplicitParam(name = "x-auth-token", value = "token login", required = true, allowEmptyValue = false, paramType = "header", example = "3122312ko3p1k23123ok123")
    public DatatableResponse find(TopicQueryFilter qf) {
        try {
            Page<MstTopicDto> page = masterDataTopicFindService.call(qf);
            return new DatatableResponse().setData(page.getContent()).setTotal(page.getTotalElements());
        } catch (Exception ex) {
            LOG.error(ex.getMessage());
            return new DatatableResponse();
        }
    }

    @PostMapping
    @ApiOperation(value = "API add topic", response = Response.class)
    @ApiResponses( value = {
            @ApiResponse(code = 200, message = "Successfully"),
            @ApiResponse(code = 500, message = "Internal Server Error")
    })
//    @ApiImplicitParam(name = "x-auth-token", value = "token login", required = true, allowEmptyValue = false, paramType = "header", example = "3122312ko3p1k23123ok123")
    public ResponseEntity add(@RequestBody @Valid TopicAddForm form, BindingResult br) {
        if (br.hasErrors()) {
            String field = br.getFieldError().getField();
            String msg = br.getFieldErrors().get(0).getDefaultMessage();
            LOG.error(field + ": " + msg);
            return Response.error(msg).build();
        }

        try {
            MstTopic topic = masterDataTopicAddService.call(form);
            MstTopicDto topicDto = TopicMapper.toDto(topic);
            return Response.ok(topicDto).build();
        }
        catch (BusinessException e) {
            LOG.error(e.getMessage());
            LOG.error(e.getClass().getCanonicalName());
            return Response.error(e.getMessage(), form).build();
        }
    }

    @PutMapping
    @ApiOperation(value = "API edit topic", response = Response.class)
    @ApiResponses( value = {
            @ApiResponse(code = 200, message = "Successfully"),
            @ApiResponse(code = 500, message = "Internal Server Error")
    })
//    @ApiImplicitParam(name = "x-auth-token", value = "token login", required = true, allowEmptyValue = false, paramType = "header", example = "3122312ko3p1k23123ok123")
    public ResponseEntity edit(@RequestBody @Valid TopicEditForm form, BindingResult br) {
        if (br.hasErrors()) {
            String field = br.getFieldError().getField();
            String msg = br.getFieldErrors().get(0).getDefaultMessage();
            LOG.error(field + ": " + msg);
            return Response.error(msg).build();
        }

        try {
            MstTopic topic = masterDataTopicEditService.call(form);
            MstTopicDto topicDto = TopicMapper.toDto(topic);
            return Response.ok(topicDto).build();
        } catch (BusinessException e) {
            LOG.error(e.getMessage());
            LOG.error(e.getClass().getCanonicalName());
            return Response.error(e.getMessage(), form).build();
        }
    }

    @DeleteMapping
    @ApiOperation(value = "API delete topic", response = Response.class)
    @ApiResponses( value = {
            @ApiResponse(code = 200, message = "Successfully"),
            @ApiResponse(code = 500, message = "Internal Server Error")
    })
//    @ApiImplicitParam(name = "x-auth-token", value = "token login", required = true, allowEmptyValue = false, paramType = "header", example = "3122312ko3p1k23123ok123")
    public ResponseEntity delete(@RequestParam String id) {
        if(!StringUtils.hasLength(id)){
            return Response.error("ID harus diisi!").build();
        }

        try {
            MstTopic topic = masterDataTopicDeleteService.call(id);
            MstTopicDto topicDto = TopicMapper.toDto(topic);
            return Response.ok(topicDto).build();
        }
        catch (BusinessException e) {
            LOG.error(e.getMessage());
            return Response.error(e.getMessage(), id).build();
        }
        catch (Exception ex) {
            LOG.error(ex.getMessage());
            return Response.error(ex.getMessage(), id).build();
        }
    }
}
