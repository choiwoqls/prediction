package com.prediction.prediction.dto.user;

import com.prediction.prediction.domain.player.Team;
import com.prediction.prediction.domain.user.User;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserDTO {

    private Long id;
    private Team team;

    @NotBlank(message = "이메일은 필수 입력 정보 입니다.")
    private String email;

    private String nickname;
    private String password;
    private int role;
    private int credit;
    private Date date;
    private int message_op;
    private int result_op;

    public UserDTO(User user){
        this.id=user.getId();
        this.team=user.getTeam();
        this.email=user.getEmail();
        this.nickname=user.getNickname();
        this.password=user.getPassword();
        this.role=user.getRole();
        this.credit=user.getCredit();
        this.date=user.getDate();
        this.message_op=user.getMessage_op();
        this.result_op=user.getResult_op();
    }
}
