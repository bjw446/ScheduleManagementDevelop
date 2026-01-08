package com.example.schedulemanagementdevelop.comment.entity;

import com.example.schedulemanagementdevelop.schedule.entity.BaseEntity;
import com.example.schedulemanagementdevelop.schedule.entity.Schedule;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Entity
@Table(name = "comments")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Comment extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String content;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "schedule_id", nullable = false)
    private Schedule schedule;

    public Comment (String content, Schedule schedule) {
        this.content = content;
        this.schedule = schedule;
    }
}
