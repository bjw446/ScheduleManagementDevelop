package com.example.schedulemanagementdevelop.user.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;

@Getter
public class UpdateUserRequest {
    @NotBlank(message = "이메일은 공란일 수 없습니다.")
    private String name;

    @NotBlank(message = "비밀번호는 필수 입력 사항입니다.")
    @Size(min = 8, message = "비밀번호는 {min}자 이상이어야 합니다")
    private String password;
}
