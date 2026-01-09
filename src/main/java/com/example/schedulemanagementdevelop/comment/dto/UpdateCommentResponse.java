package com.example.schedulemanagementdevelop.comment.dto;

import lombok.Getter;
import java.time.LocalDateTime;

@Getter
public class UpdateCommentResponse {
    private final Long id;
    private final String content;
    private final LocalDateTime modifiedAt;
    private final Long userId;
    private final Long scheduleId;

    public UpdateCommentResponse(Long id, String content, LocalDateTime modifiedAt, Long userId, Long scheduleId) {
        this.id = id;
        this.content = content;
        this.modifiedAt = modifiedAt;
        this.userId = userId;
        this.scheduleId = scheduleId;
    }
}
