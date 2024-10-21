package com.prediction.prediction.dto.community;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Community_CommentDTO {
    private Long id;
    private Long community_id;
    private Long user_id;
    private String body;
    private Date date;
}
