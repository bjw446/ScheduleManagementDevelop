package com.example.schedulemanagementdevelop.config;

import at.favre.lib.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Component;

@Component
public class PasswordEncoder { // 비밀번호 암호화 클래스 추가

    public String encode(String rawPassword) { // 입력한 비밀번호 암호화
        return BCrypt.withDefaults().hashToString(BCrypt.MIN_COST, rawPassword.toCharArray());
    }

    // 암호화된 비밀번호와 입력한 비밀번호를 암호화 하여 비교
    public boolean matches(String rawPassword, String encodedPassword) {
        BCrypt.Result result = BCrypt.verifyer().verify(rawPassword.toCharArray(), encodedPassword);
        return result.verified;
    }
}
