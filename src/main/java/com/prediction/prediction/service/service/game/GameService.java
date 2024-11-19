package com.prediction.prediction.service.service.game;

import com.prediction.prediction.domain.game.Game;
import com.prediction.prediction.dto.request.game.GameDTO;

public interface GameService {
    GameDTO createGame(GameDTO gameDto);
}
