package com.example.schedulemanagementdevelop.user.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;

@Getter
public class RegisterUserRequest {
    private String name;

    @NotBlank(message = "이메일은 공란일 수 없습니다.")
    @Email(message = "올바른 형식의 이메일이 아닙니다.")
    private String email;

    @NotBlank(message = "비밀번호는 필수 입력 사항입니다.")
    @Size(min = 8, message = "비밀번호는 {min}자 이상이어야 합니다")
    private String password;
}
