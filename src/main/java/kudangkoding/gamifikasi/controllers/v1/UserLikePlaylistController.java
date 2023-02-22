package kudangkoding.gamifikasi.controllers.v1;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import kudangkoding.gamifikasi.dto.forms.UserLikePlaylistAddForm;
import kudangkoding.gamifikasi.dto.forms.UserUnlikePlaylistAddForm;
import kudangkoding.gamifikasi.dto.mappers.UserLikePlaylistMapper;
import kudangkoding.gamifikasi.dto.models.TrxUserLikePlaylistDto;
import kudangkoding.gamifikasi.dto.queryfilters.UserLikePlaylistQueryFilter;
import kudangkoding.gamifikasi.dto.responses.DatatableResponse;
import kudangkoding.gamifikasi.dto.responses.Response;
import kudangkoding.gamifikasi.exceptions.BusinessException;
import kudangkoding.gamifikasi.models.TrxUserLikePlaylist;
import kudangkoding.gamifikasi.services.master_data.user_like_playlist.add.MasterDataUserLikePlaylistAddService;
import kudangkoding.gamifikasi.services.master_data.user_like_playlist.delete.MasterDataUserLikePlaylistDeleteService;
import kudangkoding.gamifikasi.services.master_data.user_like_playlist.find.MasterDataUserLikePlaylistFindService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/v1/users-like-playlists")
public class UserLikePlaylistController {

    private static final Logger LOG = LogManager.getLogger(UserLikePlaylistController.class);

    @Autowired private MasterDataUserLikePlaylistFindService masterDataUserLikePlaylistFindService;

    @Autowired private MasterDataUserLikePlaylistAddService masterDataUserLikePlaylistAddService;

    @Autowired private MasterDataUserLikePlaylistDeleteService masterDataUserLikePlaylistDeleteService;

    @GetMapping
    @ApiOperation(value = "API get user like playlist", response = DatatableResponse.class)
    @ApiResponses( value = {
            @ApiResponse(code = 200, message = "Successfully"),
            @ApiResponse(code = 500, message = "Internal Server Error")
    })
    //    @ApiImplicitParam(name = "x-auth-token", value = "token login", required = true, allowEmptyValue = false, paramType = "header", example = "3122312ko3p1k23123ok123")
    public DatatableResponse find(UserLikePlaylistQueryFilter qf) {
        try {
            Page <TrxUserLikePlaylistDto> page = masterDataUserLikePlaylistFindService.call(qf);
            return new DatatableResponse().setData(page.getContent()).setTotal(page.getTotalElements());
        } catch (Exception ex) {
            LOG.error(ex.getMessage());
            return new DatatableResponse();
        }
    }

    @PostMapping
    @ApiOperation(value = "API add user like playlist", response = Response.class)
    @ApiResponses( value = {
            @ApiResponse(code = 200, message = "Successfully"),
            @ApiResponse(code = 500, message = "Internal Server Error")
    })
//    @ApiImplicitParam(name = "x-auth-token", value = "token login", required = true, allowEmptyValue = false, paramType = "header", example = "3122312ko3p1k23123ok123")
    public ResponseEntity add(@RequestBody @Valid UserLikePlaylistAddForm form, BindingResult br) {
        if (br.hasErrors()) {
            String field = br.getFieldError().getField();
            String msg = br.getFieldErrors().get(0).getDefaultMessage();
            LOG.error(field + ": " + msg);
            return Response.error(msg).build();
        }

        try {
            TrxUserLikePlaylist userLikePlaylist = masterDataUserLikePlaylistAddService.call(form);
            TrxUserLikePlaylistDto userLikePlaylistDto = UserLikePlaylistMapper.toDto(userLikePlaylist);
            return Response.ok(userLikePlaylistDto).build();
        }
        catch (BusinessException e) {
            LOG.error(e.getMessage());
            LOG.error(e.getClass().getCanonicalName());
            return  Response.error(e.getMessage(), form).build();
        }
    }

    @PostMapping("/unlike")
    @ApiOperation(value = "API delete user like playlist", response = Response.class)
    @ApiResponses( value = {
            @ApiResponse(code = 200, message = "Successfully"),
            @ApiResponse(code = 500, message = "Internal Server Error")
    })
     public ResponseEntity delete(@RequestBody @Valid UserUnlikePlaylistAddForm form, BindingResult br){

        if (br.hasErrors()) {
            String field = br.getFieldError().getField();
            String msg = br.getFieldErrors().get(0).getDefaultMessage();
            LOG.error(field + ": " + msg);
            return Response.error(msg).build();
        }

        masterDataUserLikePlaylistDeleteService.call(form);
        return ResponseEntity.ok().build();

    }
}
