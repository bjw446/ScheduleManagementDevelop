package com.example.schedulemanagementdevelop.schedule.entity;

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
    private String userName;
    private String scheduleTitle;
    private String scheduleContent;

    public Schedule(String userName, String scheduleTitle, String scheduleContent) {
        this.userName = userName;
        this.scheduleTitle = scheduleTitle;
        this.scheduleContent = scheduleContent;
    }

    public void update(String userName, String scheduleTitle, String scheduleContent) {
        this.userName = userName;
        this.scheduleTitle = scheduleTitle;
        this.scheduleContent = scheduleContent;
    }
}
