package com.prediction.prediction.controller;

import com.prediction.prediction.service.service.user.UserService;
import com.prediction.prediction.util.ApiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class HelloController {

    private final UserService userService;

    //test
    @GetMapping("/hello")
    public ResponseEntity<ApiResponse<List<Object>>> hello() {
        List<Object> list = userService.info();
        System.out.println(list);
        return ApiResponse.success(list).toResponseEntity();
    }


}
