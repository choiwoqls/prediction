package com.prediction.prediction.service.service.user;

import com.prediction.prediction.domain.user.Credit_Gain;
import com.prediction.prediction.domain.user.User;
import com.prediction.prediction.dto.request.user.UserDTO;
import com.prediction.prediction.dto.response.MessageDto;
import jakarta.servlet.http.HttpServletRequest;

import java.util.List;
import java.util.Map;


public interface UserService {

    //todo response dto USER
    MessageDto signUp(UserDTO userDto);

    User getUserByEmail(String email);

    boolean checkEmail(String email);

    User getUserById(Long id);

   Credit_Gain info(HttpServletRequest request);

}
