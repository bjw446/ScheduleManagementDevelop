package com.example.schedulemanagementdevelop.comment.dto;

import lombok.Getter;

@Getter
public class UpdateCommentRequest {
    private String content;
    private Long userId;
    private Long scheduleId;
}
