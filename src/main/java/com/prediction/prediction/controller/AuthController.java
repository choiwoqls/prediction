package com.prediction.prediction.controller;

import com.prediction.prediction.dto.request.auth.CodeDTO;
import com.prediction.prediction.dto.request.auth.EmailDTO;
import com.prediction.prediction.dto.request.auth.LoginDTO;
import com.prediction.prediction.dto.request.auth.rePasswordDTO;
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
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
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

     @GetMapping("/login")
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

    //todo RequestParam Valid 추가.
    //이메일 인증 (회원가입)
    @GetMapping("/verify-email")
    @ResponseBody
    public ResponseEntity<ApiResponse<MessageDto>> verifyEmail(@RequestBody @Valid EmailDTO emailDto) {
        return ApiResponse.success(eMailService.sendMailAuth(emailDto.getEmail(), true)).toResponseEntity();
    }

    //코드 확인
    @GetMapping("/verify-code")
    public ResponseEntity<ApiResponse<MessageDto>> verifyCode(@RequestBody @Valid CodeDTO codeDto){
         return ApiResponse.success(authService.checkCode(codeDto)).toResponseEntity();
    }

    //이메일 인증 (비밀번호 찾기)
    @GetMapping("/verify-password")
    @ResponseBody
    public ResponseEntity<ApiResponse<MessageDto>> verifyPassword(@RequestBody @Valid EmailDTO emailDto) {
        return ApiResponse.success(eMailService.sendMailAuth(emailDto.getEmail(), false)).toResponseEntity();
    }

    @PutMapping("/reset-password")
    public ResponseEntity<ApiResponse<MessageDto>>resetPassword(@RequestBody @Valid rePasswordDTO rePasswordDto){
         return ApiResponse.success(authService.resetPassword(rePasswordDto)).toResponseEntity();
    }

}
