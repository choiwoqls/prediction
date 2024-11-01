package com.prediction.prediction.dto.request.game;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GameDTO {
    private Long id;
    private int home_id;
    private int away_id;
    private int status;
    private int result;
    private Date date;

}
