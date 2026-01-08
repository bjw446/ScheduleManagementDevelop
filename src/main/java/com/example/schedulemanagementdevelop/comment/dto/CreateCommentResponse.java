package com.example.schedulemanagementdevelop.comment.dto;

import lombok.Getter;
import java.time.LocalDateTime;

@Getter
public class CreateCommentResponse {
    private final Long id;
    private final String content;
    private final LocalDateTime createdAt;
    private final LocalDateTime modifiedAt;
    private final Long userId;
    private final Long scheduleId;

    public CreateCommentResponse(Long id, String content, LocalDateTime createdAt, LocalDateTime modifiedAt, Long userId, Long scheduleId) {
        this.id = id;
        this.content = content;
        this.createdAt = createdAt;
        this.modifiedAt = modifiedAt;
        this.userId = userId;
        this.scheduleId = scheduleId;
    }
}
