package com.example.schedulemanagementdevelop.user.entity;

import com.example.schedulemanagementdevelop.schedule.entity.BaseEntity;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Entity
@Table(name = "users")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class User extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false) // 공란일 수 없음
    private String name;

    @Column(nullable = false, unique = true) // 공란일 수 없음, 중복 안됨
    private String email;

    @Column(nullable = false) // 공란일 수 없음
    private String password;

    // 유저 생성자 (id를 제외한 전부 들어가야 함)
    public User(String name, String email, String password) {
        this.name = name;
        this.email = email;
        this.password = password;
    }

    // 업데이트 메서드
    public void update(String name) {
        this.name = name;
    }
}
