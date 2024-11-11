package com.prediction.prediction.dto.request.user;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class LoginDTO {

    @NotBlank(message = "이메일은 필수 입력 정보 입니다.")
    private String email;

    @NotBlank(message = "비밀번호를 입력해주세요.")
    private String password;

}
