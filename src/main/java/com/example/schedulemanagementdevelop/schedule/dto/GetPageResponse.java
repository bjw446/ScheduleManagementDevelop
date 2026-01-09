package com.example.schedulemanagementdevelop.schedule.dto;

import lombok.Getter;
import java.time.LocalDateTime;

@Getter
public class GetPageResponse {
    private final Long id;
    private final String scheduleTitle;
    private final String scheduleContent;
    private final Long commentAmount;
    private final LocalDateTime createdAt;
    private final LocalDateTime modifiedAt;
    private final String userName;

    public GetPageResponse(Long id, String scheduleTitle, String scheduleContent, Long commentAmount, LocalDateTime createdAt, LocalDateTime modifiedAt, String userName) {
        this.id = id;
        this.scheduleTitle = scheduleTitle;
        this.scheduleContent = scheduleContent;
        this.commentAmount = commentAmount;
        this.createdAt = createdAt;
        this.modifiedAt = modifiedAt;
        this.userName = userName;
    }
}
