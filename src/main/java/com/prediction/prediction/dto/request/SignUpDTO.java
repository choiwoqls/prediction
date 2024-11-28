package com.prediction.prediction.dto.request;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SignUpDTO {

    @NotNull(message = "팀 선택은 필수 입니다.")
    private Integer team_id;

    @NotBlank(message = "이메일은 필수 입력 정보 입니다.")
    @Pattern(regexp = "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$", message = "올바른 이메일 형식으로 입력해 주세요.")
    private String email;

    @NotBlank(message = "닉네임은 필수 입력 정보 입니다.")
    private String nickname;

    @NotBlank(message = "8자 이상 알파벳, 숫자, 특수문자를 포함한 비밀번호를 입력해주세요.")
    @Pattern(regexp = "^(?=.*[a-zA-Z])(?=.*\\d)(?=.*[@$!%*?&])[A-Za-z\\d@$!%*?&]{8,}$", message = "8자 이상 알파벳, 숫자, 특수문자를 포함한 비밀번호를 입력해주세요.")
    private String password;

}
