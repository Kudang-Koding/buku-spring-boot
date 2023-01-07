package kudangkoding.gamifikasi.controllers.v1;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import kudangkoding.gamifikasi.dto.forms.UserFutureAddForm;
import kudangkoding.gamifikasi.dto.forms.UserFutureEditForm;
import kudangkoding.gamifikasi.dto.mappers.UserFutureMapper;
import kudangkoding.gamifikasi.dto.models.SysUserFutureDto;
import kudangkoding.gamifikasi.dto.queryfilters.UserFutureQueryFilter;
import kudangkoding.gamifikasi.dto.responses.DatatableResponse;
import kudangkoding.gamifikasi.dto.responses.Response;
import kudangkoding.gamifikasi.exceptions.BusinessException;
import kudangkoding.gamifikasi.models.SysUserFuture;
import kudangkoding.gamifikasi.services.master_data.user_future.add.MasterDataUserFutureAddService;
import kudangkoding.gamifikasi.services.master_data.user_future.delete.MasterDataUserFutureDeleteService;
import kudangkoding.gamifikasi.services.master_data.user_future.edit.MasterDataUserFutureEditService;
import kudangkoding.gamifikasi.services.master_data.user_future.find.MasterDataUserFutureFindService;
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
@RequestMapping("/v1/users-future")
public class UserFutureController {

    private static final Logger LOG = LogManager.getLogger(UserFutureController.class);

    @Autowired private MasterDataUserFutureAddService masterDataUserFutureAddService;

    @Autowired private MasterDataUserFutureFindService masterDataUserFutureFindService;

    @Autowired private MasterDataUserFutureEditService masterDataUserFutureEditService;

    @Autowired private MasterDataUserFutureDeleteService masterDataUserFutureDeleteService;

    @GetMapping
    @ApiOperation(value = "API get user future", response = DatatableResponse.class)
    @ApiResponses( value = {
            @ApiResponse(code = 200, message = "Successfully"),
            @ApiResponse(code = 500, message = "Internal Server Error")
    })
//    @ApiImplicitParam(name = "x-auth-token", value = "token login", required = true, allowEmptyValue = false, paramType = "header", example = "3122312ko3p1k23123ok123")
    public DatatableResponse find(UserFutureQueryFilter qf) {
        try {
            Page<SysUserFutureDto> page = masterDataUserFutureFindService.call(qf);
            return new DatatableResponse().setData(page.getContent()).setTotal(page.getTotalElements());
        } catch (Exception ex) {
            LOG.error(ex.getMessage());
            return new DatatableResponse();
        }
    }

    @PostMapping
    @ApiOperation(value = "API add user future", response = Response.class)
    @ApiResponses( value = {
            @ApiResponse(code = 200, message = "Successfully"),
            @ApiResponse(code = 500, message = "Internal Server Error")
    })
//    @ApiImplicitParam(name = "x-auth-token", value = "token login", required = true, allowEmptyValue = false, paramType = "header", example = "3122312ko3p1k23123ok123")
    public ResponseEntity add(@RequestBody @Valid UserFutureAddForm form, BindingResult br) {
        if (br.hasErrors()) {
            String field = br.getFieldError().getField();
            String msg = br.getFieldErrors().get(0).getDefaultMessage();
            LOG.error(field + ": " + msg);
            return Response.error(msg).build();
        }

        try {
            SysUserFuture userFuture = masterDataUserFutureAddService.call(form);
            SysUserFutureDto userFutureDto = UserFutureMapper.toDto(userFuture);
            return Response.ok(userFutureDto).build();
        }
        catch (BusinessException e) {
            LOG.error(e.getMessage());
            LOG.error(e.getClass().getCanonicalName());
            return  Response.error(e.getMessage(), form).build();
        }
    }

    @PutMapping
    @ApiOperation(value = "API edit user future", response = Response.class)
    @ApiResponses( value = {
            @ApiResponse(code = 200, message = "Successfully"),
            @ApiResponse(code = 500, message = "Internal Server Error")
    })
//    @ApiImplicitParam(name = "x-auth-token", value = "token login", required = true, allowEmptyValue = false, paramType = "header", example = "3122312ko3p1k23123ok123")
    public ResponseEntity edit(@RequestBody @Valid UserFutureEditForm form, BindingResult br) {
        if (br.hasErrors()) {
            String field = br.getFieldError().getField();
            String msg = br.getFieldErrors().get(0).getDefaultMessage();
            LOG.error(field + ": " + msg);
            return Response.error(msg).build();
        }

        try {
            SysUserFuture userFuture = masterDataUserFutureEditService.call(form);
            SysUserFutureDto userFutureDto = UserFutureMapper.toDto(userFuture);
            return Response.ok(userFutureDto).build();
        }
        catch (BusinessException e) {
            LOG.error(e.getMessage());
            LOG.error(e.getClass().getCanonicalName());
            return Response.error(e.getMessage(), form).build();
        }
    }

    @DeleteMapping
    @ApiOperation(value = "API delete user future", response = Response.class)
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
            SysUserFuture userFuture = masterDataUserFutureDeleteService.call(id);
            SysUserFutureDto userFutureDto = UserFutureMapper.toDto(userFuture);
            return Response.ok(userFutureDto).build();
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
