package com.prediction.prediction.dto.request.game;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Game_PlayDTO {
    private Long id;
    private Long game_id;
    private Long user_id;
    private int expect;
    private int result;

}
