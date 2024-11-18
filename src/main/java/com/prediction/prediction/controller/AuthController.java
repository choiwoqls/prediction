package com.prediction.prediction.controller;

import com.prediction.prediction.dto.request.user.LoginDTO;
import com.prediction.prediction.dto.request.user.UserDTO;
import com.prediction.prediction.dto.response.MessageDto;
import com.prediction.prediction.service.user.AuthService;
import com.prediction.prediction.service.user.UserService;
import com.prediction.prediction.util.ApiResponse;
import com.prediction.prediction.util.JWTAuthenticationResponse;
import com.prediction.prediction.util.JwtUtil;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Pattern;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/auth")
@CrossOrigin(origins = "*", methods = { RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT, RequestMethod.PUT,
        RequestMethod.DELETE, RequestMethod.PATCH, RequestMethod.OPTIONS })
@RequiredArgsConstructor
public class AuthController {

    private final UserService userService;
    private final AuthService authService;

     @PostMapping("/login")
     public ResponseEntity<ApiResponse<JWTAuthenticationResponse>> login(@RequestBody @Valid LoginDTO loginDto){
         System.out.println("login");
         JWTAuthenticationResponse jwtAuthenticationResponse = authService.login(loginDto);
         return ApiResponse.success(jwtAuthenticationResponse).toResponseEntity();
     }

    @PostMapping("/sign-up")
    public ResponseEntity<ApiResponse<MessageDto>> signUp(@RequestBody @Valid UserDTO userDto) {
        MessageDto message = userService.signUp(userDto);
        return ApiResponse.success(message).toResponseEntity();
    }

    @GetMapping("/check")
    public ResponseEntity<ApiResponse<String>> check(
            @RequestBody @Pattern(regexp = "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$", message = "올바른 이메일 형식으로 입력해 주세요.") String email) {

        return ApiResponse.success(email).toResponseEntity();
    }


}
