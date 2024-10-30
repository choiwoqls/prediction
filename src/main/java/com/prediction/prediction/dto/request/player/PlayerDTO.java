package com.prediction.prediction.dto.request.player;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PlayerDTO {
    private Long id;
    private int team_id;
    private String name;
    private int position;
    private String hand;

}
