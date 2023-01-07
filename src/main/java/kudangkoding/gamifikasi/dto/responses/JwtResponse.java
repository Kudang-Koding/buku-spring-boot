package kudangkoding.gamifikasi.dto.responses;

import java.util.List;

public class JwtResponse {
    private String id;
    private String email;
    private List<String> roles;
    private String accessToken;
    private String tokenType = "Bearer";

    public JwtResponse(String accessToken, String id, String email, List<String> roles) {
        this.id = id;
		this.email = email;
		this.roles = roles;
        this.accessToken = accessToken;
    }

    public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

    public String getAccessToken() {
        return accessToken;
    }

    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }

    public String getTokenType() {
        return tokenType;
    }

    public void setTokenType(String tokenType) {
        this.tokenType = tokenType;
    }

    public List<String> getRoles() {
		return roles;
	}
}

