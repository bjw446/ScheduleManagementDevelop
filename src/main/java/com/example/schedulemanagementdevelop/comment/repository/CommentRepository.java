package com.example.schedulemanagementdevelop.comment.repository;

import com.example.schedulemanagementdevelop.comment.entity.Comment;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface CommentRepository extends JpaRepository <Comment, Long> {
    // 스케줄ID를 기준으로 댓글 리스트 구현
    List<Comment> findAllByScheduleId(Long scheduleId);
    // 댓글이 등록된 일정 삭제시 등록된 댓글도 같이 삭제
    void deleteAllByScheduleId(Long scheduleId);
}
