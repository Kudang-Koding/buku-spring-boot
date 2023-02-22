package kudangkoding.gamifikasi.controllers.v1;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import kudangkoding.gamifikasi.dto.forms.PlaylistUserViewAddForm;
import kudangkoding.gamifikasi.dto.mappers.PlaylistUserViewMapper;
import kudangkoding.gamifikasi.dto.models.TrxPlaylistUserViewDto;
import kudangkoding.gamifikasi.dto.models.VwPlaylistUserViewDto;
import kudangkoding.gamifikasi.dto.models.VwPlaylistUserViewWithTotalRuntimeDto;
import kudangkoding.gamifikasi.dto.queryfilters.PlaylistUserViewQueryFilter;
import kudangkoding.gamifikasi.dto.queryfilters.PlaylistUserViewWithTotalRuntimeQueryFilter;
import kudangkoding.gamifikasi.dto.responses.DatatableResponse;
import kudangkoding.gamifikasi.dto.responses.Response;
import kudangkoding.gamifikasi.exceptions.BusinessException;
import kudangkoding.gamifikasi.models.TrxPlaylistUserView;
import kudangkoding.gamifikasi.services.master_data.playlist_user_view.add_video.MasterDataPlaylistUserViewAddVideoService;
import kudangkoding.gamifikasi.services.master_data.playlist_user_view.find.MasterDataPlaylistUserViewFindService;
import kudangkoding.gamifikasi.services.master_data.playlist_user_view.find_with_total_runtime.MasterDataPlaylistUserViewWithTotalRuntimeFindService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/v1/playlist-user-view")
public class PlaylistUserViewController {

    private static final Logger LOG = LogManager.getLogger(PlaylistUserViewController.class);

    @Autowired private MasterDataPlaylistUserViewAddVideoService masterDataPlaylistUserViewAddService;

    @Autowired private MasterDataPlaylistUserViewFindService masterDataPlaylistUserViewFindService;

    @Autowired private MasterDataPlaylistUserViewWithTotalRuntimeFindService masterDataPlaylistUserViewWithTotalRuntimeFindService;

    @PostMapping
    @ApiOperation(value = "API add playlist user view", response = Response.class)
    @ApiResponses( value = {
            @ApiResponse(code = 200, message = "Successfully"),
            @ApiResponse(code = 500, message = "Internal Server Error")
    })
//    @ApiImplicitParam(name = "x-auth-token", value = "token login", required = true, allowEmptyValue = false, paramType = "header", example = "3122312ko3p1k23123ok123")
    public ResponseEntity add(@RequestBody @Valid PlaylistUserViewAddForm form, BindingResult br) {
        if (br.hasErrors()) {
            String field = br.getFieldError().getField();
            String msg = br.getFieldErrors().get(0).getDefaultMessage();
            LOG.error(field + ": " + msg);
            return Response.error(msg).build();
        }

        try {
            TrxPlaylistUserView playlistUserView = masterDataPlaylistUserViewAddService.call(form);
            TrxPlaylistUserViewDto playlistUserViewDto = PlaylistUserViewMapper.toDto(playlistUserView);
            return Response.ok(playlistUserViewDto).build();
        } catch (BusinessException e) {
            LOG.error(e.getMessage());
            LOG.error(e.getClass().getCanonicalName());
            return  Response.error(e.getMessage(), form).build();
        }
    }

    @GetMapping
    @ApiOperation(value = "API get playlist user view", response = DatatableResponse.class)
    @ApiResponses( value = {
            @ApiResponse(code = 200, message = "Successfully"),
            @ApiResponse(code = 500, message = "Internal Server Error")
    })
//    @ApiImplicitParam(name = "x-auth-token", value = "token login", required = true, allowEmptyValue = false, paramType = "header", example = "3122312ko3p1k23123ok123")
    public DatatableResponse find(PlaylistUserViewQueryFilter qf) {
        try {
            Page<VwPlaylistUserViewDto> page = masterDataPlaylistUserViewFindService.call(qf);
            return new DatatableResponse().setData(page.getContent()).setTotal(page.getTotalElements());
        } catch (Exception ex){
            LOG.error(ex.getMessage());
            return new DatatableResponse();
        }
    }

    @GetMapping("/getPlaylistWithTotalWatchTime")
    @ApiOperation(value = "API get the last playlist video watched by the user with total watch", response = DatatableResponse.class)
    @ApiResponses( value = {
            @ApiResponse(code = 200, message = "Successfully"),
            @ApiResponse(code = 500, message = "Internal Server Error")
    })
    public DatatableResponse find(PlaylistUserViewWithTotalRuntimeQueryFilter qf) {
        try {
            Page<VwPlaylistUserViewWithTotalRuntimeDto> page = masterDataPlaylistUserViewWithTotalRuntimeFindService.call(qf);

            return new DatatableResponse().setData(page.getContent()).setTotal(page.getTotalElements());
        } catch (Exception ex){
            LOG.error(ex.getMessage());
            return new DatatableResponse();
        }
    }

}
