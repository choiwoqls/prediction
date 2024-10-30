package com.prediction.prediction.dto.request.player;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Player_RecordDTO {
    private Long id;
    private Long player_id;
    private int player_role;
    private float avg;
    private int hr_win;
    private float obp_lose;
    private float slg_hold;
    private float ops_save;
    private float war;
}
