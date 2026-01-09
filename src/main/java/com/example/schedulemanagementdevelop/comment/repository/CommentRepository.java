package com.example.schedulemanagementdevelop.comment.repository;

import com.example.schedulemanagementdevelop.comment.entity.Comment;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface CommentRepository extends JpaRepository <Comment, Long> {
    List<Comment> findAllByScheduleId(Long scheduleId);
    void deleteAllByScheduleId(Long scheduleId);
}
