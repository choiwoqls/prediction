package com.prediction.prediction.controller;

import com.prediction.prediction.dto.request.game.GameDTO;
import com.prediction.prediction.service.service.game.GameService;
import com.prediction.prediction.util.ApiResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/admin")
@CrossOrigin(origins = "*", methods = { RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT, RequestMethod.PUT,
        RequestMethod.DELETE, RequestMethod.PATCH, RequestMethod.OPTIONS })
@RequiredArgsConstructor
public class AdminController {

    @Autowired
    private final GameService gameService;

    @PostMapping("/game")
    public ResponseEntity<ApiResponse<GameDTO>> createGame(@RequestBody @Valid GameDTO gameDto){
        GameDTO newGame = gameService.createGame(gameDto);
        return new ApiResponse<>(newGame).toResponseEntity();
    }

}
