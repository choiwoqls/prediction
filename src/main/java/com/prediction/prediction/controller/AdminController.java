package com.prediction.prediction.controller;

import com.prediction.prediction.dto.request.game.GameDTO;
import com.prediction.prediction.dto.response.MessageDto;
import com.prediction.prediction.exception.BadRequestAlertException;
import com.prediction.prediction.service.service.game.GameService;
import com.prediction.prediction.util.ApiResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
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
        GameDTO newGame = gameService.saveGame(gameDto);
        return new ApiResponse<>(newGame).toResponseEntity();
    }

    @PutMapping("/game")
    public ResponseEntity<ApiResponse<GameDTO>> updateGame(@RequestBody @Valid GameDTO gameDto){
        GameDTO newGame = gameService.saveGame(gameDto);
        return new ApiResponse<>(newGame).toResponseEntity();
    }

    @PutMapping("/game/result")
    public ResponseEntity<ApiResponse<MessageDto>> resultGame(@RequestParam int result, @RequestParam Long game_id){
        //todo result: Valid 0 <= result <= 4 , Game: isExisted game Valid
        if(result < 1 || result > 3){
            throw new BadRequestAlertException("잘못된 result 입력");
        }
        MessageDto message = gameService.resultGame(result, game_id);
        return new ApiResponse<>(message).toResponseEntity();
    }

    @PutMapping("/game/cancel")
    public ResponseEntity<ApiResponse<MessageDto>> cancelGame(@RequestParam Long game_id){
        MessageDto message = gameService.cancelGame(game_id);
        return new ApiResponse<>(message).toResponseEntity();
    }

}
