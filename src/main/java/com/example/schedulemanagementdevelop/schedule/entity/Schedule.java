package com.example.schedulemanagementdevelop.schedule.entity;

import com.example.schedulemanagementdevelop.user.entity.User;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Entity
@Table(name = "schedules")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Schedule extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String scheduleTitle;
    private String scheduleContent;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    public Schedule(String scheduleTitle, String scheduleContent, User user) {
        this.scheduleTitle = scheduleTitle;
        this.scheduleContent = scheduleContent;
        this.user = user;
    }

    public void update(String scheduleTitle, String scheduleContent, User user) {
        this.scheduleTitle = scheduleTitle;
        this.scheduleContent = scheduleContent;
        this.user = user;
    }
}
