package com.example.schedulemanagementdevelop.schedule.dto;

import lombok.Getter;

@Getter
public class CreateScheduleRequest {
    private String scheduleTitle;
    private String scheduleContent;
    private Long userId;
}
