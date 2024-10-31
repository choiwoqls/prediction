package com.prediction.prediction.service.user;

import com.prediction.prediction.dto.request.user.UserDTO;
import com.prediction.prediction.util.JWTAuthenticationResponse;

public interface AuthService {

    public JWTAuthenticationResponse login (UserDTO userDto);


}
