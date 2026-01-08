package com.example.schedulemanagementdevelop.schedule.dto;

import lombok.Getter;
import java.time.LocalDateTime;

@Getter
public class UpdateScheduleResponse {
    private final Long id;
    private final String scheduleTitle;
    private final String scheduleContent;
    private final LocalDateTime modifiedAt;

    public UpdateScheduleResponse(Long id, String scheduleTitle, String scheduleContent, LocalDateTime modifiedAt) {
        this.id = id;
        this.scheduleTitle = scheduleTitle;
        this.scheduleContent = scheduleContent;
        this.modifiedAt = modifiedAt;
    }
}
