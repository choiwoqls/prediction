package com.prediction.prediction.dto.response.user;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.prediction.prediction.domain.user.User;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
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

    @NotNull(message = "팀 선택은 필수 입니다.")
    private Integer team_id;

    @NotBlank(message = "이메일은 필수 입력 정보 입니다.")
    @Pattern(regexp = "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$", message = "올바른 이메일 형식으로 입력해 주세요.")
    private String email;

    @NotBlank(message = "닉네임은 필수 입력 정보 입니다.")
    private String nickname;

    @JsonIgnore
    @NotBlank(message = "8자 이상 알파벳, 숫자, 특수문자를 포함한 비밀번호를 입력해주세요.")
    @Pattern(regexp = "^(?=.*[a-zA-Z])(?=.*\\d)(?=.*[@$!%*?&])[A-Za-z\\d@$!%*?&]{8,}$", message = "8자 이상 알파벳, 숫자, 특수문자를 포함한 비밀번호를 입력해주세요.")
    private String password;

    private int credit;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss", timezone = "Asia/Seoul")
    private Date date;

    private int message_op;
    private int result_op;

    public UserDTO(User user) {
        this.id = user.getId();
        this.team_id = user.getTeam().getId();
        this.email = user.getEmail();
        this.nickname = user.getNickname();
        this.password = user.getPassword();
        this.credit = user.getCredit();
        this.date = user.getDate();
        this.message_op = user.getMessage_op();
        this.result_op = user.getResult_op();
    }
}
