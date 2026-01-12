package com.example.schedulemanagementdevelop.schedule.dto;

import com.example.schedulemanagementdevelop.comment.dto.GetCommentResponse;
import lombok.Getter;
import java.time.LocalDateTime;
import java.util.List;

@Getter // 스케줄 및 댓글 같이 조회하기 위한 dto
public class GetScheduleAndCommentResponse {
    private final Long id;
    private final String scheduleTitle;
    private final String scheduleContent;
    private final LocalDateTime modifiedAt;
    private final List<GetCommentResponse> commentList;

    public GetScheduleAndCommentResponse(Long id, String scheduleTitle, String scheduleContent, LocalDateTime modifiedAt, List<GetCommentResponse> commentList) {
        this.id = id;
        this.scheduleTitle = scheduleTitle;
        this.scheduleContent = scheduleContent;
        this.modifiedAt = modifiedAt;
        this.commentList = commentList;
    }
}
