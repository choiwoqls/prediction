package com.prediction.prediction.controller;

import com.prediction.prediction.dto.request.auth.CodeDTO;
import com.prediction.prediction.dto.request.auth.EmailDTO;
import com.prediction.prediction.dto.request.auth.LoginDTO;
import com.prediction.prediction.dto.request.user.UserDTO;
import com.prediction.prediction.dto.response.MessageDto;
import com.prediction.prediction.mail.EmailService;
import com.prediction.prediction.service.user.AuthService;
import com.prediction.prediction.service.user.UserService;
import com.prediction.prediction.util.ApiResponse;
import com.prediction.prediction.util.JWTAuthenticationResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/auth")
@CrossOrigin(origins = "*", methods = { RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT, RequestMethod.PUT,
        RequestMethod.DELETE, RequestMethod.PATCH, RequestMethod.OPTIONS })
@RequiredArgsConstructor
public class AuthController {

    private final UserService userService;
    private final AuthService authService;
    private final EmailService eMailService;

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

    @GetMapping("/verify-email")
    @ResponseBody
    public ResponseEntity<ApiResponse<String>> check(@RequestBody @Valid EmailDTO emailDto) {
        return ApiResponse.success(eMailService.sendMailReject(emailDto.getEmail())).toResponseEntity();
    }

    @GetMapping("/verify-code")
    public ResponseEntity<ApiResponse<MessageDto>> checkCode(@RequestBody @Valid CodeDTO codeDto){
         return ApiResponse.success(authService.checkCode(codeDto)).toResponseEntity();
    }


}
