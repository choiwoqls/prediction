package com.prediction.prediction.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {

    @GetMapping("/hello")
    public String hello() {
        return "Hello World";
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
