package com.example.schedulemanagementdevelop.comment.dto;

import lombok.Getter;

@Getter
public class CreateCommentRequest {
    private String content;
    private Long userId;
    private Long scheduleId;
}
