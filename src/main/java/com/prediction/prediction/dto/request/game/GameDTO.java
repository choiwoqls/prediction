package com.prediction.prediction.dto.request.game;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.prediction.prediction.domain.game.Game;
import com.prediction.prediction.validator.MinDaysFromToday;
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

    @NotNull(message = "Away Team ID를 입력하시오.")
    private Integer away_id;

    private int status;

    private int result;

    @NotNull(message = "경기 날짜를 입력해주세요.")
    //@MinDaysFromToday(days = 3, message = "3일 뒤 경기부터 입력할 수 있습니다.")
    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm")
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
