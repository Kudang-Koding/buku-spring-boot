package kudangkoding.gamifikasi.controllers.v1;

import javax.validation.Valid;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import kudangkoding.gamifikasi.dto.forms.FutureAddForm;
import kudangkoding.gamifikasi.dto.forms.FutureEditForm;
import kudangkoding.gamifikasi.dto.mappers.FutureMapper;
import kudangkoding.gamifikasi.dto.models.MstFutureDto;
import kudangkoding.gamifikasi.dto.queryfilters.FutureQueryFilter;
import kudangkoding.gamifikasi.dto.responses.DatatableResponse;
import kudangkoding.gamifikasi.dto.responses.Response;
import kudangkoding.gamifikasi.exceptions.BusinessException;
import kudangkoding.gamifikasi.models.MstFuture;
import kudangkoding.gamifikasi.services.master_data.future.add.MasterDataFutureAddService;
import kudangkoding.gamifikasi.services.master_data.future.delete.MasterDataFutureDeleteService;
import kudangkoding.gamifikasi.services.master_data.future.edit.MasterDataFutureEditService;
import kudangkoding.gamifikasi.services.master_data.future.find.MasterDataFutureFindService;
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
@RequestMapping("/v1/future")
public class FutureController {

    private static final Logger LOG = LogManager.getLogger(FutureController.class);

	@Autowired private MasterDataFutureAddService masterDataFutureAddService;
    @Autowired private MasterDataFutureEditService masterDataFutureEditService;
    @Autowired private MasterDataFutureFindService masterDataFutureFindService;
    @Autowired private MasterDataFutureDeleteService masterDataFutureDeleteService;

    @GetMapping
    @ApiOperation(value = "API get future", response = DatatableResponse.class)
    @ApiResponses( value = {
            @ApiResponse(code = 200, message = "Successfully"),
            @ApiResponse(code = 500, message = "Internal Server Error")
    })
//    @ApiImplicitParam(name = "x-auth-token", value = "token login", required = true, allowEmptyValue = false, paramType = "header", example = "3122312ko3p1k23123ok123")
    public DatatableResponse find(FutureQueryFilter qf){
        try {
            Page<MstFutureDto> page = masterDataFutureFindService.call(qf);
            return new DatatableResponse().setData(page.getContent()).setTotal(page.getTotalElements());
        } catch (Exception ex){
            LOG.error(ex.getMessage());
            return new DatatableResponse();
        }
    }

    @PostMapping
    @ApiOperation(value = "API add future", response = Response.class)
    @ApiResponses( value = {
            @ApiResponse(code = 200, message = "Successfully"),
            @ApiResponse(code = 500, message = "Internal Server Error")
    })
//    @ApiImplicitParam(name = "x-auth-token", value = "token login", required = true, allowEmptyValue = false, paramType = "header", example = "3122312ko3p1k23123ok123")
    public ResponseEntity add(@RequestBody @Valid FutureAddForm form, BindingResult br){
        if(br.hasErrors()){
            String field = br.getFieldError().getField();
            String msg = br.getFieldErrors().get(0).getDefaultMessage();
            LOG.error(field + ": " + msg);
            return Response.error(msg).build();
        }

        try {
            MstFuture future = masterDataFutureAddService.call(form);
            MstFutureDto futureDto = FutureMapper.toDto(future);
            return Response.ok(futureDto).build();
        }
        catch (BusinessException e){
            LOG.error(e.getMessage());
            LOG.error(e.getClass().getCanonicalName());
            return Response.error(e.getMessage(), form).build();
        }
    }

    @PutMapping
    @ApiOperation(value = "API edit future", response = Response.class)
    @ApiResponses( value = {
            @ApiResponse(code = 200, message = "Successfully"),
            @ApiResponse(code = 500, message = "Internal Server Error")
    })
//    @ApiImplicitParam(name = "x-auth-token", value = "token login", required = true, allowEmptyValue = false, paramType = "header", example = "3122312ko3p1k23123ok123")
    public ResponseEntity edit(@RequestBody @Valid FutureEditForm form, BindingResult br){
        if(br.hasErrors()){
            String field = br.getFieldError().getField();
            String msg = br.getFieldErrors().get(0).getDefaultMessage();
            LOG.error(field + ": " + msg);
            return Response.error(msg).build();
        }

        try {
            MstFuture future = masterDataFutureEditService.call(form);
            MstFutureDto futureDto = FutureMapper.toDto(future);
            return Response.ok(futureDto).build();
        }
        catch (BusinessException e){
            LOG.error(e.getMessage());
            LOG.error(e.getClass().getCanonicalName());
            return Response.error(e.getMessage(), form).build();
        }
    }

    @DeleteMapping
    @ApiOperation(value = "API delete future", response = Response.class)
    @ApiResponses( value = {
            @ApiResponse(code = 200, message = "Successfully"),
            @ApiResponse(code = 500, message = "Internal Server Error")
    })
//    @ApiImplicitParam(name = "x-auth-token", value = "token login", required = true, allowEmptyValue = false, paramType = "header", example = "3122312ko3p1k23123ok123")
    public ResponseEntity delete(@RequestParam String id){
        if(!StringUtils.hasLength(id)){
            return Response.error("ID harus diisi!").build();
        }

        try {
            MstFuture future = masterDataFutureDeleteService.call(id);
            MstFutureDto futureDto = FutureMapper.toDto(future);
            return Response.ok(futureDto).build();
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
