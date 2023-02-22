package kudangkoding.gamifikasi.controllers.v1;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import kudangkoding.gamifikasi.dto.models.TrxPlaylistUserViewVideoDto;
import kudangkoding.gamifikasi.dto.models.VwPlaylistUserViewVideoWithTotalRuntimeDto;
import kudangkoding.gamifikasi.dto.queryfilters.PlaylistUserViewVideoQueryFilter;
import kudangkoding.gamifikasi.dto.queryfilters.PlaylistUserViewVideoWithTotalRuntimeQueryFilter;
import kudangkoding.gamifikasi.dto.responses.DatatableResponse;
import kudangkoding.gamifikasi.dto.responses.Response;
import kudangkoding.gamifikasi.services.master_data.playlist_user_view_video.delete.MasterDataPlaylistUserViewVideoDeleteService;
import kudangkoding.gamifikasi.services.master_data.playlist_user_view_video.find.MasterDataPlaylistUserViewVideoService;
import kudangkoding.gamifikasi.services.master_data.playlist_user_view_video.find_with_total_runtime.MasterDataPlaylistUserViewVideoWithTotalRuntimeFindService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/v1/last-video-watched")
public class PlaylistUserViewVideoController {

    @Autowired
    MasterDataPlaylistUserViewVideoService masterDataPlaylistUserViewVideoService;

    @Autowired
    MasterDataPlaylistUserViewVideoDeleteService masterDataPlaylistUserViewVideoDeleteService;

    @Autowired
    MasterDataPlaylistUserViewVideoWithTotalRuntimeFindService masterDataPlaylistUserViewVideoWithTotalRuntimeFindService;

    private static final Logger LOG = LogManager.getLogger(PlaylistUserViewVideoController.class);

    @PostMapping
    @ApiOperation(value = "API reset the total watch time of the user's video", response = DatatableResponse.class)
    @ApiResponses( value = {
            @ApiResponse(code = 200, message = "Successfully"),
            @ApiResponse(code = 500, message = "Internal Server Error")
    })
    public ResponseEntity resetRunTime(PlaylistUserViewVideoQueryFilter qf) {
        try {
            TrxPlaylistUserViewVideoDto page = masterDataPlaylistUserViewVideoDeleteService.call(qf);
            return ResponseEntity.ok(page);
        } catch (Exception ex){
            LOG.error(ex.getMessage());
            return Response.error(ex.getMessage()).build();
        }
    }

    @GetMapping()
    @ApiOperation(value = "API get the last video watched by the user", response = DatatableResponse.class)
    @ApiResponses( value = {
            @ApiResponse(code = 200, message = "Successfully"),
            @ApiResponse(code = 500, message = "Internal Server Error")
    })
    public ResponseEntity find(PlaylistUserViewVideoQueryFilter qf) {
        try {
            TrxPlaylistUserViewVideoDto page = masterDataPlaylistUserViewVideoService.call(qf);
            return ResponseEntity.ok(page);
        } catch (Exception ex){
            LOG.error(ex.getMessage());
            return Response.error(ex.getMessage()).build();
        }
    }

    @GetMapping("/getWithTotalWatchTime")
    @ApiOperation(value = "API get the last video watched by the user with total watch", response = DatatableResponse.class)
    @ApiResponses( value = {
            @ApiResponse(code = 200, message = "Successfully"),
            @ApiResponse(code = 500, message = "Internal Server Error")
    })
    public DatatableResponse find(PlaylistUserViewVideoWithTotalRuntimeQueryFilter qf) {
        try {
            Page<VwPlaylistUserViewVideoWithTotalRuntimeDto> page = masterDataPlaylistUserViewVideoWithTotalRuntimeFindService.call(qf);

            return new DatatableResponse().setData(page.getContent()).setTotal(page.getTotalElements());
        } catch (Exception ex){
            LOG.error(ex.getMessage());
            return new DatatableResponse();
        }
    }
}
