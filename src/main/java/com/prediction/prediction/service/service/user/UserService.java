package com.prediction.prediction.service.service.user;

import com.prediction.prediction.domain.user.User;
import com.prediction.prediction.dto.request.SignUpDTO;
import com.prediction.prediction.dto.response.user.Credit_GainDTO;
import com.prediction.prediction.dto.response.user.UserDTO;
import com.prediction.prediction.dto.response.MessageDto;
import jakarta.servlet.http.HttpServletRequest;

import java.util.List;


public interface UserService {

    //todo response dto USER
    MessageDto signUp(SignUpDTO signUpDto);

    User getUserByEmail(String email);

    boolean checkEmail(String email);

    UserDTO getUserById(Long id);

    UserDTO getUserByToken(HttpServletRequest request);

    List<Credit_GainDTO> info(HttpServletRequest request);

    void addCredit(User user, int type, int credit);

    void addCredit(List<User> success_list, int type, int credit);

}
