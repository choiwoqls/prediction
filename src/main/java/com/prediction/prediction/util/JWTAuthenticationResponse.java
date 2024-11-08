package com.prediction.prediction.util;

import com.prediction.prediction.domain.user.User;
import lombok.Data;

@Data
public class JWTAuthenticationResponse {
    private String accessToken;
    private String tokenType = "Bearer";

    public JWTAuthenticationResponse(String accessToken) {
        this.accessToken = accessToken;
    }
}
