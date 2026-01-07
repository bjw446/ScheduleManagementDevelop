package com.example.schedulemanagementdevelop.schedule.dto;

import lombok.Getter;

@Getter
public class UpdateScheduleRequest {
    private String userName;
    private String scheduleTitle;
    private String scheduleContent;
}
