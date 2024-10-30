package com.prediction.prediction.dto.request.user;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class LineupDTO {
    private Long id;
    private Long user_id;
    private Long player_id;
    private int defence;
    private int attack;
}
