package com.example.schedulemanagementdevelop.user.dto;

import jakarta.validation.constraints.Size;
import lombok.Getter;

@Getter
public class UpdateUserRequest {
    private String name;
    private String email;
    private String password;
}
