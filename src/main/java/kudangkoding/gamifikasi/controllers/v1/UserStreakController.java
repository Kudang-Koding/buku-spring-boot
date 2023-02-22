package kudangkoding.gamifikasi.controllers.v1;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import kudangkoding.gamifikasi.dto.forms.UserStreakAddForm;
import kudangkoding.gamifikasi.dto.mappers.UserStreakMapper;
import kudangkoding.gamifikasi.dto.models.TrxUserStreakDto;
import kudangkoding.gamifikasi.dto.queryfilters.UserStreakQueryFilter;
import kudangkoding.gamifikasi.dto.responses.Response;
import kudangkoding.gamifikasi.exceptions.BusinessException;
import kudangkoding.gamifikasi.models.TrxUserStreak;
import kudangkoding.gamifikasi.services.master_data.user_streak.add.MasterDataUserStreakAddService;
import kudangkoding.gamifikasi.services.master_data.user_streak.find.MasterDataUserStreakFindService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/v1/users-streak")
public class UserStreakController {

    private static final Logger LOG = LogManager.getLogger(UserStreakController.class);

    @Autowired private MasterDataUserStreakAddService masterDataUserStreakAddService;

    @Autowired private MasterDataUserStreakFindService masterDataUserStreakFindService;

    @PostMapping
    @ApiOperation(value = "API add user streak", response = Response.class)
    @ApiResponses( value = {
            @ApiResponse(code = 200, message = "Successfully"),
            @ApiResponse(code = 500, message = "Internal Server Error")
    })
//    @ApiImplicitParam(name = "x-auth-token", value = "token login", required = true, allowEmptyValue = false, paramType = "header", example = "3122312ko3p1k23123ok123")
    public ResponseEntity add(@RequestBody @Valid UserStreakAddForm form, BindingResult br) {
        if (br.hasErrors()) {
            String field = br.getFieldError().getField();
            String msg = br.getFieldErrors().get(0).getDefaultMessage();
            LOG.error(field + ": " + msg);
            return Response.error(msg).build();
        }

        try {
            TrxUserStreak userStreak = masterDataUserStreakAddService.call(form);
            TrxUserStreakDto trxUserStreakDto = UserStreakMapper.toDto(userStreak);
            return Response.ok(trxUserStreakDto).build();
        }
        catch (BusinessException e) {
            LOG.error(e.getMessage());
            LOG.error(e.getClass().getCanonicalName());
            return  Response.error(e.getMessage(), form).build();
        }
    }

    @GetMapping("get-streak")
    @ApiOperation(value = "API get user streak", response = Response.class)
    @ApiResponses( value = {
            @ApiResponse(code = 200, message = "Successfully"),
            @ApiResponse(code = 500, message = "Internal Server Error")
    })
//    @ApiImplicitParam(name = "x-auth-token", value = "token login", required = true, allowEmptyValue = false, paramType = "header", example = "3122312ko3p1k23123ok123")
    public ResponseEntity getStreak(UserStreakQueryFilter qf) {
        if (!StringUtils.hasLength(qf.getUserId())) {
            LOG.error("User id harus diisi!");
            throw new BusinessException("User id harus diisi!");
        }

        return ResponseEntity.ok(masterDataUserStreakFindService.getStreak(qf.getUserId()));
    }

}
