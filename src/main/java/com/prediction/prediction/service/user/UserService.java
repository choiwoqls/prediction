package com.prediction.prediction.service.user;

import com.prediction.prediction.domain.user.User;
import com.prediction.prediction.dto.request.user.UserDTO;

import java.util.List;


public interface UserService {

    User signUp(UserDTO userDto);

    User getUserByEmail(String email);

    User getUserById(Long id);

    List<Object[]> info();

}
