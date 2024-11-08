package com.prediction.prediction.controller;

import com.prediction.prediction.domain.user.User;
import com.prediction.prediction.dto.request.user.UserDTO;
import com.prediction.prediction.dto.response.MessageDto;
import com.prediction.prediction.service.user.AuthService;
import com.prediction.prediction.service.user.UserService;
import com.prediction.prediction.util.ApiResponse;

import com.prediction.prediction.util.JWTAuthenticationResponse;
import com.prediction.prediction.util.JwtUtil;
import jakarta.servlet.http.HttpServletRequest;
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

     @PostMapping("/logout")
     public ResponseEntity<ApiResponse<MessageDto>> logout(HttpServletRequest request){
         String token = JwtUtil.getJwtFromRequest(request);
         MessageDto message = authService.logout(token);
         return ApiResponse.success(message).toResponseEntity();
     }


    @PostMapping("/sign-up")
    public ResponseEntity<ApiResponse<MessageDto>> signUp(@RequestBody @Valid UserDTO userDto) {
        System.out.println("team_id : " + userDto.getTeam());
        MessageDto message = userService.signUp(userDto);
        return ApiResponse.success(message).toResponseEntity();

    }

}
