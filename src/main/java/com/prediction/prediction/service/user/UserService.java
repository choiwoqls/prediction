package com.prediction.prediction.service.user;

import com.prediction.prediction.common.exception.InvalidateUserException;
import com.prediction.prediction.domain.user.User;
import com.prediction.prediction.dto.user.UserDTO;
import com.prediction.prediction.repository.user.UserRepository;
import com.prediction.prediction.response.SingleResponse;
import jakarta.validation.ValidationException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    public UserDTO emailCheck(String email) {
        System.out.println("checkecke");
        User user = (User) userRepository.findByEmail(email).orElseThrow(InvalidateUserException::new);
        UserDTO dto = new UserDTO(user);
        return dto;
    }

}
