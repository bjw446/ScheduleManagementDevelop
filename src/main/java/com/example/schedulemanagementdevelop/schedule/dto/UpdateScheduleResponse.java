package com.example.schedulemanagementdevelop.schedule.dto;

import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class UpdateScheduleResponse {
    private final Long id;
    private final String userName;
    private final String scheduleTitle;
    private final String scheduleContent;
    private final LocalDateTime createdAt;
    private final LocalDateTime modifiedAt;

    public UpdateScheduleResponse(Long id, String userName, String scheduleTitle, String scheduleContent, LocalDateTime createdAt, LocalDateTime modifiedAt) {
        this.id = id;
        this.userName = userName;
        this.scheduleTitle = scheduleTitle;
        this.scheduleContent = scheduleContent;
        this.createdAt = createdAt;
        this.modifiedAt = modifiedAt;
    }
}
