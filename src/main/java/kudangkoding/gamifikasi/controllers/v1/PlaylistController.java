package kudangkoding.gamifikasi.controllers.v1;

import javax.validation.Valid;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import kudangkoding.gamifikasi.dto.forms.PlaylistAddForm;
import kudangkoding.gamifikasi.dto.forms.PlaylistEditForm;
import kudangkoding.gamifikasi.dto.mappers.PlaylistMapper;
import kudangkoding.gamifikasi.dto.models.MstPlaylistDto;
import kudangkoding.gamifikasi.dto.queryfilters.PlaylistQueryFilter;
import kudangkoding.gamifikasi.dto.responses.DatatableResponse;
import kudangkoding.gamifikasi.dto.responses.Response;
import kudangkoding.gamifikasi.exceptions.BusinessException;
import kudangkoding.gamifikasi.models.MstPlaylist;
import kudangkoding.gamifikasi.services.master_data.playlist.add.MasterDataPlaylistAddService;
import kudangkoding.gamifikasi.services.master_data.playlist.delete.MasterDataPlaylistDeleteService;
import kudangkoding.gamifikasi.services.master_data.playlist.edit.MasterDataPlaylistEditService;
import kudangkoding.gamifikasi.services.master_data.playlist.find.MasterDataPlaylistFindService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import java.util.List;


@RestController
@RequestMapping("/v1/playlist")
public class PlaylistController {

    private static final Logger LOG = LogManager.getLogger(PlaylistController.class);

    @Autowired private MasterDataPlaylistAddService masterDataPlaylistAddService;
    @Autowired private MasterDataPlaylistEditService masterDataPlaylistEditService;
    @Autowired private MasterDataPlaylistFindService masterDataPlaylistFindService;
    @Autowired private MasterDataPlaylistDeleteService masterDataPlaylistDeleteService;

    @GetMapping
    @ApiOperation(value = "API get playlist", response = DatatableResponse.class)
    @ApiResponses( value = {
            @ApiResponse(code = 200, message = "Successfully"),
            @ApiResponse(code = 500, message = "Internal Server Error")
    })
    @ApiImplicitParam(name = "x-auth-token", value = "token login", required = true, allowEmptyValue = false, paramType = "header", example = "3122312ko3p1k23123ok123")
    public DatatableResponse find(PlaylistQueryFilter qf){
        try {
            Page<MstPlaylistDto> page = masterDataPlaylistFindService.call(qf);
            return new DatatableResponse().setData(page.getContent()).setTotal(page.getTotalElements());
        } catch (Exception ex){
            LOG.error(ex.getMessage());
            return new DatatableResponse();
        }
    }

    @PostMapping
    @ApiOperation(value = "API add playlist", response = Response.class)
    @ApiResponses( value = {
            @ApiResponse(code = 200, message = "Successfully"),
            @ApiResponse(code = 500, message = "Internal Server Error")
    })
    @ApiImplicitParam(name = "x-auth-token", value = "token login", required = true, allowEmptyValue = false, paramType = "header", example = "3122312ko3p1k23123ok123")
    public ResponseEntity add(@RequestBody @Valid PlaylistAddForm form, BindingResult br){
        if(br.hasErrors()){
            String field = br.getFieldError().getField();
            String msg = br.getFieldErrors().get(0).getDefaultMessage();
            LOG.error(field + ": " + msg);
            return Response.error(msg).build();
        }

        try {
            MstPlaylist playlist = masterDataPlaylistAddService.call(form);
            MstPlaylistDto playlistDto = PlaylistMapper.toDto(playlist);
            return Response.ok(playlistDto).build();
        }
        catch (BusinessException e){
            LOG.error(e.getMessage());
            LOG.error(e.getClass().getCanonicalName());
            return Response.error(e.getMessage(), form).build();
        }
    }

    @PutMapping
    @ApiOperation(value = "API edit playlist", response = Response.class)
    @ApiResponses( value = {
            @ApiResponse(code = 200, message = "Successfully"),
            @ApiResponse(code = 500, message = "Internal Server Error")
    })
    @ApiImplicitParam(name = "x-auth-token", value = "token login", required = true, allowEmptyValue = false, paramType = "header", example = "3122312ko3p1k23123ok123")
    public ResponseEntity edit(@RequestBody @Valid PlaylistEditForm form, BindingResult br){
        if(br.hasErrors()){
            String field = br.getFieldError().getField();
            String msg = br.getFieldErrors().get(0).getDefaultMessage();
            LOG.error(field + ": " + msg);
            return Response.error(msg).build();
        }

        try {
            MstPlaylist playlist = masterDataPlaylistEditService.call(form);
            MstPlaylistDto playlistDto = PlaylistMapper.toDto(playlist);
            return Response.ok(playlistDto).build();
        }
        catch (BusinessException e){
            LOG.error(e.getMessage());
            LOG.error(e.getClass().getCanonicalName());
            return Response.error(e.getMessage(), form).build();
        }
    }

    @DeleteMapping
    @ApiOperation(value = "API delete playlist", response = Response.class)
    @ApiResponses( value = {
            @ApiResponse(code = 200, message = "Successfully"),
            @ApiResponse(code = 500, message = "Internal Server Error")
    })
    @ApiImplicitParam(name = "x-auth-token", value = "token login", required = true, allowEmptyValue = false, paramType = "header", example = "3122312ko3p1k23123ok123")
    public ResponseEntity delete(@RequestParam String id){
        if(!StringUtils.hasLength(id)){
            return Response.error("ID harus diisi!").build();
        }

        try {
            MstPlaylist playlist = masterDataPlaylistDeleteService.call(id);
            MstPlaylistDto playlistDto = PlaylistMapper.toDto(playlist);
            return Response.ok(playlistDto).build();
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
