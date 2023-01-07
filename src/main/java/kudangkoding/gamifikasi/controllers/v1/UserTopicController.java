package kudangkoding.gamifikasi.controllers.v1;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import kudangkoding.gamifikasi.dto.forms.UserTopicAddForm;
import kudangkoding.gamifikasi.dto.forms.UserTopicEditForm;
import kudangkoding.gamifikasi.dto.mappers.UserTopicMapper;
import kudangkoding.gamifikasi.dto.models.SysUserTopicDto;
import kudangkoding.gamifikasi.dto.queryfilters.UserTopicQueryFilter;
import kudangkoding.gamifikasi.dto.responses.DatatableResponse;
import kudangkoding.gamifikasi.dto.responses.Response;
import kudangkoding.gamifikasi.exceptions.BusinessException;
import kudangkoding.gamifikasi.models.SysUserTopic;
import kudangkoding.gamifikasi.services.master_data.user_topic.add.MasterDataUserTopicAddService;
import kudangkoding.gamifikasi.services.master_data.user_topic.delete.MasterDataUserTopicDeleteService;
import kudangkoding.gamifikasi.services.master_data.user_topic.edit.MasterDataUserTopicEditService;
import kudangkoding.gamifikasi.services.master_data.user_topic.find.MasterDataUserTopicFindService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/v1/users-topics")
public class UserTopicController {

    private static final Logger LOG = LogManager.getLogger(UserTopicController.class);

    @Autowired private MasterDataUserTopicAddService masterDataUserTopicAddService;

    @Autowired private MasterDataUserTopicFindService masterDataUserTopicFindService;

    @Autowired private MasterDataUserTopicEditService masterDataUserTopicEditService;

    @Autowired private MasterDataUserTopicDeleteService masterDataUserTopicDeleteService;

    @GetMapping
    @ApiOperation(value = "API get user topic", response = DatatableResponse.class)
    @ApiResponses( value = {
            @ApiResponse(code = 200, message = "Successfully"),
            @ApiResponse(code = 500, message = "Internal Server Error")
    })
//    @ApiImplicitParam(name = "x-auth-token", value = "token login", required = true, allowEmptyValue = false, paramType = "header", example = "3122312ko3p1k23123ok123")
    public DatatableResponse find(UserTopicQueryFilter qf) {
        try {
            Page<SysUserTopicDto> page = masterDataUserTopicFindService.call(qf);
            return new DatatableResponse().setData(page.getContent()).setTotal(page.getTotalElements());
        } catch (Exception ex) {
            LOG.error(ex.getMessage());
            return new DatatableResponse();
        }
    }

    @PostMapping
    @ApiOperation(value = "API add user topic", response = Response.class)
    @ApiResponses( value = {
            @ApiResponse(code = 200, message = "Successfully"),
            @ApiResponse(code = 500, message = "Internal Server Error")
    })
//    @ApiImplicitParam(name = "x-auth-token", value = "token login", required = true, allowEmptyValue = false, paramType = "header", example = "3122312ko3p1k23123ok123")
    public ResponseEntity add(@RequestBody @Valid UserTopicAddForm form, BindingResult br) {
        if (br.hasErrors()) {
            String field = br.getFieldError().getField();
            String msg = br.getFieldErrors().get(0).getDefaultMessage();
            LOG.error(field + ": " + msg);
            return Response.error(msg).build();
        }

        try {
            SysUserTopic userTopic = masterDataUserTopicAddService.call(form);
            SysUserTopicDto userTopicDto = UserTopicMapper.toDto(userTopic);
            return Response.ok(userTopicDto).build();
        }
        catch (BusinessException e) {
            LOG.error(e.getMessage());
            LOG.error(e.getClass().getCanonicalName());
            return  Response.error(e.getMessage(), form).build();
        }
    }

    @PutMapping
    @ApiOperation(value = "API edit user topic", response = Response.class)
    @ApiResponses( value = {
            @ApiResponse(code = 200, message = "Successfully"),
            @ApiResponse(code = 500, message = "Internal Server Error")
    })
//    @ApiImplicitParam(name = "x-auth-token", value = "token login", required = true, allowEmptyValue = false, paramType = "header", example = "3122312ko3p1k23123ok123")
    public ResponseEntity edit(@RequestBody @Valid UserTopicEditForm form, BindingResult br) {
        if (br.hasErrors()) {
            String field = br.getFieldError().getField();
            String msg = br.getFieldErrors().get(0).getDefaultMessage();
            LOG.error(field + ": " + msg);
            return Response.error(msg).build();
        }

        try {
          SysUserTopic userTopic = masterDataUserTopicEditService.call(form);
          SysUserTopicDto userTopicDto = UserTopicMapper.toDto(userTopic);
          return Response.ok(userTopicDto).build();
        } catch (BusinessException e) {
            LOG.error(e.getMessage());
            LOG.error(e.getClass().getCanonicalName());
            return Response.error(e.getMessage(), form).build();
        }
    }

    @DeleteMapping
    @ApiOperation(value = "API delete user topic", response = Response.class)
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
            SysUserTopic userTopic = masterDataUserTopicDeleteService.call(id);
            SysUserTopicDto userTopicDto = UserTopicMapper.toDto(userTopic);
            return Response.ok(userTopicDto).build();
        }
        catch (BusinessException e){
            LOG.error(e.getMessage());
            return Response.error(e.getMessage(), id).build();
        }
        catch (Exception ex){
            LOG.error(ex.getMessage());
            return Response.error(ex.getMessage(), id).build();
        }
    }
}
