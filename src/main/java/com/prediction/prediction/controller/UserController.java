package com.prediction.prediction.controller;

import com.prediction.prediction.dto.response.MessageDto;
import com.prediction.prediction.service.user.AuthService;
import com.prediction.prediction.util.ApiResponse;

import com.prediction.prediction.util.JwtUtil;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
@CrossOrigin(origins = "*", methods = { RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT, RequestMethod.PUT,
        RequestMethod.DELETE, RequestMethod.PATCH, RequestMethod.OPTIONS })
@RequiredArgsConstructor
public class UserController {

    private final AuthService authService;

@PostMapping("/logout")
     public ResponseEntity<ApiResponse<MessageDto>> logout(HttpServletRequest request){
         String token = JwtUtil.getJwtFromRequest(request);
         MessageDto message = authService.logout(token);
         return ApiResponse.success(message).toResponseEntity();
     }

}
