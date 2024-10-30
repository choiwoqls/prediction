package com.prediction.prediction.dto.jwt;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Builder
@Data
@AllArgsConstructor
public class TokenInfo {

    // JWT 대한 인증 타입 -Bearer
    private String grantType;

    private String accessToken;

    private String refreshToken;
}
