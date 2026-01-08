package com.example.schedulemanagementdevelop.user.dto;

import lombok.Getter;

@Getter
public class LoginUserResponse {
    private final Long id;
    private final String name;
    private final String email;

    public LoginUserResponse(Long id, String name, String email) {
        this.id = id;
        this.name = name;
        this.email = email;
    }
}
