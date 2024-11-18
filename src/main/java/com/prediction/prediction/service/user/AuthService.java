package com.prediction.prediction.service.user;

import com.prediction.prediction.dto.request.auth.CodeDTO;
import com.prediction.prediction.dto.request.auth.LoginDTO;
import com.prediction.prediction.dto.response.MessageDto;
import com.prediction.prediction.util.JWTAuthenticationResponse;

public interface AuthService {

    public JWTAuthenticationResponse login (LoginDTO loginDto);
    public MessageDto logout(String token);
    public MessageDto checkCode(CodeDTO codeDto);
}
