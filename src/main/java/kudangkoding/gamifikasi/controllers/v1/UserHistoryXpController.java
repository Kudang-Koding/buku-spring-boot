package kudangkoding.gamifikasi.controllers.v1;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import kudangkoding.gamifikasi.dto.enums.UserHistoryXPTypeCode;
import kudangkoding.gamifikasi.dto.forms.UserHistoryXpAddForm;
import kudangkoding.gamifikasi.dto.mappers.UserHistoryXpMapper;
import kudangkoding.gamifikasi.dto.models.TrxUserHistoryXpDto;
import kudangkoding.gamifikasi.dto.responses.Response;
import kudangkoding.gamifikasi.exceptions.BusinessException;
import kudangkoding.gamifikasi.exceptions.InvalidParameterValueException;
import kudangkoding.gamifikasi.models.TrxUserHistoryXp;
import kudangkoding.gamifikasi.services.master_data.user_history_xp.add.MasterDataUserHistoryXpAddService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/v1/users-history-xp")
public class UserHistoryXpController {

    private static final Logger LOG = LogManager.getLogger(UserHistoryXpController.class);

    @Autowired private MasterDataUserHistoryXpAddService masterDataUserHistoryXpAddService;

    @PostMapping
    @ApiOperation(value = "API add user history xp", response = Response.class)
    @ApiResponses( value = {
            @ApiResponse(code = 200, message = "Successfully"),
            @ApiResponse(code = 500, message = "Internal Server Error")
    })
//    @ApiImplicitParam(name = "x-auth-token", value = "token login", required = true, allowEmptyValue = false, paramType = "header", example = "3122312ko3p1k23123ok123")
    public ResponseEntity add(@RequestBody @Valid UserHistoryXpAddForm form, BindingResult br) {
        if (br.hasErrors()) {
            String field = br.getFieldError().getField();
            String msg = br.getFieldErrors().get(0).getDefaultMessage();
            LOG.error(field + ": " + msg);
            return Response.error(msg).build();
        }

        try {
            TrxUserHistoryXp userHistoryXp = masterDataUserHistoryXpAddService.call(form);
            TrxUserHistoryXpDto trxUserHistoryXpDto = UserHistoryXpMapper.toDto(userHistoryXp);
            return Response.ok(trxUserHistoryXpDto).build();
        }
        catch (BusinessException e) {
            LOG.error(e.getMessage());
            LOG.error(e.getClass().getCanonicalName());
            return  Response.error(e.getMessage(), form).build();
        }
    }

    @PostMapping("add-playlist-video")
    @ApiOperation(value = "API tambah history user saat selesai menonton video dalam playlist")
    @ApiResponses( value = {
            @ApiResponse(code = 200, message = "Successfully"),
            @ApiResponse(code = 500, message = "Internal Server Error")
    })
//    @ApiImplicitParam(name = "x-auth-token", value = "token login", required = true, allowEmptyValue = false, paramType = "header", example = "3122312ko3p1k23123ok123")
    public ResponseEntity addXp(@RequestBody @Valid UserHistoryXpAddForm form, BindingResult br) {
        if(br.hasErrors()){
            String field = br.getFieldError().getField();
            String msg = br.getFieldErrors().get(0).getDefaultMessage();
            LOG.error(field + ": " + msg);
            throw new InvalidParameterValueException("Invalid parameter " + field + " value");
        }

        UserHistoryXpAddForm userHistoryXpAddForm = new UserHistoryXpAddForm()
                .setUser_id(form.getUser_id()).setDate(form.getDate()).setType(UserHistoryXPTypeCode.WATCH_VIDEO.val())
                .setPlaylist_video_id(form.getPlaylist_video_id());

        TrxUserHistoryXp userHistoryXp = masterDataUserHistoryXpAddService.call(userHistoryXpAddForm);

        return ResponseEntity.ok(userHistoryXp);
    }
}
