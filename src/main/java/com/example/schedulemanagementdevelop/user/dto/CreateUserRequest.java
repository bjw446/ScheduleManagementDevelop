package com.example.schedulemanagementdevelop.user.dto;

import jakarta.validation.constraints.Size;
import lombok.Getter;

@Getter
public class CreateUserRequest {
    private String name;
    private String email;
    @Size(min = 8, message = "비밀번호는 {min}자 이상이어야 합니다.")
    private String password;
}
