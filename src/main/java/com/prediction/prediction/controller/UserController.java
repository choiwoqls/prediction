package com.prediction.prediction.controller;

import com.prediction.prediction.dto.user.UserDTO;
import com.prediction.prediction.response.ResponseService;
import com.prediction.prediction.response.SingleResponse;
import com.prediction.prediction.service.user.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/user")
@CrossOrigin(origins = "*", methods = {RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT, RequestMethod.PUT, RequestMethod.DELETE, RequestMethod.PATCH, RequestMethod.OPTIONS})
@RequiredArgsConstructor
public class UserController {


    private final UserService userService;
    private final ResponseService responseService;


    @PostMapping("/login")
    public SingleResponse<UserDTO> login(@RequestBody @Valid UserDTO userDto){
        System.out.println("asdasd");

        return responseService.getSingleResponse(userService.emailCheck(userDto.getEmail()));
    }





}



