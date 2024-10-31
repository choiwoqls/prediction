package com.prediction.prediction.util;

import com.prediction.prediction.domain.user.User;
import lombok.Data;

@Data
public class JWTAuthenticationResponse {
    private String accessToken;
    private String tokenType = "Bearer";
    private User user;

    public JWTAuthenticationResponse(String accessToken , User user) {
        this.accessToken = accessToken;
        this.user = user;
    }
}
