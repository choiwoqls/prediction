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

    @GetMapping("/hello")
    public ResponseEntity<ApiResponse<List<Object>>> hello() {
        System.out.println("????????????????asd");
        List<Object> list = userService.info();
        System.out.println(list);
        return ApiResponse.success(list).toResponseEntity();
    }

    @GetMapping("/api1")
    public ResponseEntity<String> api1() {
        return new ResponseEntity<String>("only user", HttpStatus.OK);
    }

    @GetMapping("/api2")
    public ResponseEntity<String> api2() {
        return new ResponseEntity<String>("only admin", HttpStatus.OK);

    }

    @GetMapping("/user")
    public ResponseEntity<String> user(@AuthenticationPrincipal UserDetails userDetails) {
        return new ResponseEntity<String>(userDetails.getUsername(), HttpStatus.OK);
    }

}
