package kudangkoding.gamifikasi.controllers.v1;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import kudangkoding.gamifikasi.dto.forms.UserEditForm;
import kudangkoding.gamifikasi.dto.mappers.UserMapper;
import kudangkoding.gamifikasi.dto.models.SysUserDto;
import kudangkoding.gamifikasi.dto.queryfilters.UserQueryFilter;
import kudangkoding.gamifikasi.dto.responses.DatatableResponse;
import kudangkoding.gamifikasi.dto.responses.Response;
import kudangkoding.gamifikasi.exceptions.BusinessException;
import kudangkoding.gamifikasi.models.SysUser;
import kudangkoding.gamifikasi.repositories.SysUserRepository;
import kudangkoding.gamifikasi.services.master_data.user.edit.MasterDataUserEditService;
import kudangkoding.gamifikasi.services.master_data.user.find.MasterDataUserFindService;
import kudangkoding.gamifikasi.services.master_data.user_detail.UserDetailsServiceImpl;
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
@RequestMapping("/v1/users")
public class UserController {

    private static final Logger LOG = LogManager.getLogger(UserController.class);

    @Autowired private SysUserRepository sysUserRepository;

    @Autowired private UserDetailsServiceImpl userService;

    @Autowired private MasterDataUserFindService masterDataUserFindService;

    @Autowired private MasterDataUserEditService masterDataUserEditService;



    @GetMapping
    public DatatableResponse find(UserQueryFilter qf) {
        try {
            Page<SysUserDto> page = masterDataUserFindService.call(qf);
            return new DatatableResponse().setData(page.getContent()).setTotal(page.getTotalElements());
        } catch (Exception ex){
            LOG.error(ex.getMessage());
            return new DatatableResponse();
        }
    }

    @PutMapping
    @ApiOperation(value = "API edit user", response = Response.class)
    @ApiResponses( value = {
            @ApiResponse(code = 200, message = "Successfully"),
            @ApiResponse(code = 500, message = "Internal Server Error")
    })
//    @ApiImplicitParam(name = "x-auth-token", value = "token login", required = true, allowEmptyValue = false, paramType = "header", example = "3122312ko3p1k23123ok123")
    public ResponseEntity edit(@RequestBody @Valid UserEditForm form, BindingResult br) {
        if(br.hasErrors()){
            String field = br.getFieldError().getField();
            String msg = br.getFieldErrors().get(0).getDefaultMessage();
            LOG.error(field + ": " + msg);
            return Response.error(msg).build();
        }

        try {
            SysUser user = masterDataUserEditService.call(form);
            SysUserDto userDto = UserMapper.toDto(user);
            return Response.ok(userDto).build();
        }
        catch (BusinessException e) {
            LOG.error(e.getMessage());
            LOG.error(e.getClass().getCanonicalName());
            return Response.error(e.getMessage(), form).build();
        }
    }
}

