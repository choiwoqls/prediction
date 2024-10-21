package com.prediction.prediction.dto.user;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserDTO {

    private Long id;
    private int team_id;
    private String email;
    private String nickname;
    private String password;
    private int role;
    private int credit;
    private Date date;
    private int message_op;
    private int result_op;
}
