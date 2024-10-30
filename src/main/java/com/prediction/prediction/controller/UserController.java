package com.prediction.prediction.controller;

import com.prediction.prediction.domain.player.Team;
import com.prediction.prediction.domain.user.User;
import com.prediction.prediction.dto.request.player.TeamDTO;
import com.prediction.prediction.dto.request.user.UserDTO;
import com.prediction.prediction.service.user.UserService;
import com.prediction.util.ApiResponse;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
@CrossOrigin(origins = "*", methods = { RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT, RequestMethod.PUT,
        RequestMethod.DELETE, RequestMethod.PATCH, RequestMethod.OPTIONS })
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    // @PostMapping("/login")
    // public SingleResponse<UserDTO> login(@RequestBody @Valid UserDTO userDto){
    // System.out.println("asdasd");

    // return
    // responseService.getSingleResponse(userService.emailCheck(userDto.getEmail()));
    // }

    // @PostMapping("/duplication_email")
    // public SingleResponse<UserDTO> duplication_email(@RequestBody @Valid String
    // email){

    // return
    // responseService.getSingleResponse(userService.emailCheck(userDto.getEmail()));
    // }

    // @PostMapping("/duplication_nickname")
    // public SingleResponse<UserDTO> duplication_nickname(@RequestBody @Valid
    // String nickname){

    // return
    // responseService.getSingleResponse(userService.emailCheck(userDto.getEmail()));
    // }

    @PostMapping("/sign-up")
    public ResponseEntity<ApiResponse<User>> signUp(@RequestBody @Valid UserDTO userDto) {
        System.out.println("team_id : " + userDto.getTeam());
        User user = userService.signUp(userDto);
        return ApiResponse.success(user).toResponseEntity();

    }

}
