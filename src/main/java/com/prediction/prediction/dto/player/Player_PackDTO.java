package com.prediction.prediction.dto.player;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Player_PackDTO {
    private Long id;
    private String name;
    private int price;
    private int status;

}
