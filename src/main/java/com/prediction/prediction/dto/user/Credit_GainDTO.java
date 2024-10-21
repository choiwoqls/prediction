package com.prediction.prediction.dto.user;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Credit_GainDTO {
    private Long id;
    private Long user_id;
    private int credit;
    private int type;
    private Date date;

}

