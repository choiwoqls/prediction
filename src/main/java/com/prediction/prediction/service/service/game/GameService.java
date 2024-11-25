package com.prediction.prediction.service.service.game;

import com.prediction.prediction.domain.game.Game;
import com.prediction.prediction.dto.request.game.GameDTO;
import com.prediction.prediction.dto.response.MessageDto;

public interface GameService {
    GameDTO saveGame(GameDTO gameDto);
    MessageDto resultGame(int result, Long game_id);
}
