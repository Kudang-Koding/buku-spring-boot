package kudangkoding.gamifikasi.dto.requests;

import java.util.Set;

import javax.validation.constraints.*;


public class LoginByGoogleRequest {
    
    @NotBlank(message = "Token Id harus diisi|Id token required")
    private String id_token;

    public String getIdToken() {
        return id_token;
    }

    public void setIdToken(String id_token) {
        this.id_token = id_token;
    }


}
