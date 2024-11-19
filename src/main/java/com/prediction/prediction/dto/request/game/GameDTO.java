package com.prediction.prediction.dto.request.game;

import com.prediction.prediction.domain.game.Game;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GameDTO {
    private Long id;

    @NotNull(message = "Home Team ID를 입력하시오.")
    private Integer home_id;

    @NotNull(message = "away Team ID를 입력하시오.")
    private Integer away_id;

    private int status;

    private int result;

    @NotNull(message = "경기 날짜를 입력해주세요.")
    private Date date;

    public GameDTO(Game game){
        this.id = game.getId();
        this.home_id = game.getHome().getId();
        this.away_id = game.getAway().getId();
        this.status = game.getStatus();
        this.result = game.getResult();
        this.date = game.getDate();
    }

}
