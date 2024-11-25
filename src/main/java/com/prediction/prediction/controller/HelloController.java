package com.prediction.prediction.controller;

import com.prediction.prediction.domain.user.Credit_Gain;
import com.prediction.prediction.domain.user.User;
import com.prediction.prediction.dto.request.user.Credit_GainDTO;
import com.prediction.prediction.service.service.user.Credit_GainService;
import com.prediction.prediction.service.service.user.UserService;
import com.prediction.prediction.util.ApiResponse;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequiredArgsConstructor
public class HelloController {

    private final UserService userService;

    private final Credit_GainService creditGainService;

    //test
    @GetMapping("/hello")
    public ResponseEntity<ApiResponse<Map<String, Object>>> hello(HttpServletRequest request) {
        Map<String, Object> info = new HashMap<>();
        info.put("credit_gain", userService.info(request));
        info.put("user", userService.getUserByToken(request));
        return ApiResponse.success(info).toResponseEntity();
    }


}
