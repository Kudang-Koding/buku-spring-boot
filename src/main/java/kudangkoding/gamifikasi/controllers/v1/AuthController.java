package kudangkoding.gamifikasi.controllers.v1;

import kudangkoding.gamifikasi.configs.jwt.JwtUtils;
import kudangkoding.gamifikasi.dto.forms.AuthenticationSignInWithUidForm;
import kudangkoding.gamifikasi.dto.mappers.UserMapper;
import kudangkoding.gamifikasi.dto.models.AuthenticationDto;
import kudangkoding.gamifikasi.dto.responses.Response;
import kudangkoding.gamifikasi.exceptions.BusinessException;
import kudangkoding.gamifikasi.models.SysUser;
import kudangkoding.gamifikasi.services.authentications.signin_with_uid.AuthenticationSignInWithUidService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;

import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import javax.validation.Valid;

@RestController
@RequestMapping("/v1/auth")
public class AuthController {

    private static final Logger LOG = LogManager.getLogger(AuthController.class);

    @Autowired private AuthenticationSignInWithUidService authenticationSignInWithUidService;

    @Autowired private JwtUtils jwtUtils;

    @PostMapping("/signin/with_uid")
    public ResponseEntity signinWithUid(@Valid @RequestBody AuthenticationSignInWithUidForm form, BindingResult br) {
        if(br.hasErrors()){
            String field = br.getFieldError().getField();
            String msg = br.getFieldErrors().get(0).getDefaultMessage();
            LOG.error(field + ": " + msg);
            return Response.error(msg).build();
        }

        try {
            SysUser user = authenticationSignInWithUidService.call(form);
            String token = jwtUtils.createToken(user.getId());
            AuthenticationDto result = UserMapper.toAuthenticationDto(user, token, jwtUtils.getExpirationTime());
            return Response.ok(result).build();
        }
        catch (BusinessException e){
            LOG.error(e.getMessage());
            LOG.error(e.getClass().getCanonicalName());
            return Response.error(e.getMessage(), form).build();
        }
    }
}
