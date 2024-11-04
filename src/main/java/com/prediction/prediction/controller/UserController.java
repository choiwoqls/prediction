package com.prediction.prediction.controller;

import com.prediction.prediction.domain.user.User;
import com.prediction.prediction.dto.request.user.UserDTO;
import com.prediction.prediction.service.user.AuthService;
import com.prediction.prediction.service.user.UserService;
import com.prediction.prediction.util.ApiResponse;

import com.prediction.prediction.util.JWTAuthenticationResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
@CrossOrigin(origins = "*", methods = { RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT, RequestMethod.PUT,
        RequestMethod.DELETE, RequestMethod.PATCH, RequestMethod.OPTIONS })
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;
    private final AuthService authService;

     @PostMapping("/login")
     public ResponseEntity<ApiResponse<JWTAuthenticationResponse>> login(@RequestBody @Valid UserDTO userDto){
         System.out.println("login");
         JWTAuthenticationResponse jwtAuthenticationResponse = authService.login(userDto);
         return ApiResponse.success(jwtAuthenticationResponse).toResponseEntity();
     }


    @PostMapping("/sign-up")
    public ResponseEntity<ApiResponse<User>> signUp(@RequestBody @Valid UserDTO userDto) {
        System.out.println("team_id : " + userDto.getTeam());
        User user = userService.signUp(userDto);
        return ApiResponse.success(user).toResponseEntity();

    }

}
