package com.prediction.prediction.dto.player;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TeamDTO {
    private int id;
    private String name;
    private int win_count;
    private int draw_count;
    private int lose_count;
}
