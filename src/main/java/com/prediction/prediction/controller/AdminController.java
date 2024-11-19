package com.prediction.prediction.controller;

import com.prediction.prediction.util.ApiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/admin")
public class AdminController {

//    @Autowired
//    private final GameService gameService;


    @PostMapping("/game")
    public ResponseEntity<ApiResponse<String>> createGame(){
        System.out.println("ADMIN TEST");



        return new ApiResponse<>("test").toResponseEntity();
    }

}
