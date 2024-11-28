package com.prediction.prediction.controller;

import com.prediction.prediction.service.service.user.Credit_GainService;
import com.prediction.prediction.service.service.user.UserService;
import com.prediction.prediction.util.ApiResponse;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
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
