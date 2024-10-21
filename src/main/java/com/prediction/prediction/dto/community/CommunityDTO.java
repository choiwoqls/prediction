package com.prediction.prediction.dto.community;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CommunityDTO {
    private Long id;
    private Long user_id;
    private int lineup_check;
    private int type;
    private String title;
    private String body;
    private Date date;
}
