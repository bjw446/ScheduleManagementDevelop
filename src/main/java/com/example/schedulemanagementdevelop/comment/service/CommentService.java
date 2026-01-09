package com.example.schedulemanagementdevelop.comment.service;

import com.example.schedulemanagementdevelop.comment.dto.*;
import com.example.schedulemanagementdevelop.comment.entity.Comment;
import com.example.schedulemanagementdevelop.comment.repository.CommentRepository;
import com.example.schedulemanagementdevelop.schedule.entity.Schedule;
import com.example.schedulemanagementdevelop.schedule.repository.ScheduleRepository;
import com.example.schedulemanagementdevelop.user.entity.User;
import com.example.schedulemanagementdevelop.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CommentService {
    private final CommentRepository commentRepository;
    private final ScheduleRepository scheduleRepository;
    private final UserRepository userRepository;

    @Transactional
    public CreateCommentResponse createComment(CreateCommentRequest request) {
        User user = userRepository.findById(request.getUserId()).orElseThrow(
                () -> new IllegalArgumentException("없는 유저 입니다.")
        );

        Schedule schedule = scheduleRepository.findById(request.getScheduleId()).orElseThrow(
                () -> new IllegalArgumentException("없는 일정 입니다.")
        );

        Comment comment = new Comment(request.getContent(), schedule);
        Comment savedComment = commentRepository.save(comment);
        return new CreateCommentResponse(
                savedComment.getId(),
                savedComment.getContent(),
                savedComment.getCreatedAt(),
                savedComment.getModifiedAt(),
                savedComment.getSchedule().getUser().getId(),
                savedComment.getSchedule().getId()
        );
    }

    @Transactional(readOnly = true)
    public List<GetCommentResponse> findAllComment() {
        List<Comment> comments = commentRepository.findAll();
        List<GetCommentResponse> dtos = new ArrayList<>();
        for (Comment comment : comments) {
            GetCommentResponse dto = new GetCommentResponse(
                    comment.getId(),
                    comment.getContent(),
                    comment.getCreatedAt(),
                    comment.getModifiedAt(),
                    comment.getSchedule().getUser().getId(),
                    comment.getSchedule().getId()
            );
            dtos.add(dto);
        }
        return dtos;
    }

    @Transactional(readOnly = true)
    public GetCommentResponse findOneComment(Long commentId) {
        Comment comment = commentRepository.findById(commentId).orElseThrow(
                () -> new IllegalArgumentException("없는 댓글 입니다.")
        );

        return new GetCommentResponse(
                comment.getId(),
                comment.getContent(),
                comment.getCreatedAt(),
                comment.getModifiedAt(),
                comment.getSchedule().getUser().getId(),
                comment.getSchedule().getId()
        );
    }

    @Transactional
    public UpdateCommentResponse updateComment(Long commentId, UpdateCommentRequest request) {
        User user = userRepository.findById(request.getUserId()).orElseThrow(
                () -> new IllegalArgumentException("없는 유저 입니다.")
        );

        Schedule schedule = scheduleRepository.findById(request.getScheduleId()).orElseThrow(
                () -> new IllegalArgumentException("없는 일정 입니다.")
        );

        Comment comment = commentRepository.findById(commentId).orElseThrow(
                () -> new IllegalArgumentException("없는 댓글 입니다.")
        );

        comment.update(request.getContent(), comment.getSchedule());
        return new UpdateCommentResponse(
                comment.getId(),
                comment.getContent(),
                comment.getModifiedAt(),
                comment.getSchedule().getUser().getId(),
                comment.getSchedule().getId()
        );
    }

    @Transactional
    public void deleteComment(Long commentId) {
        boolean existence = commentRepository.existsById(commentId);

        if (!existence) {
            throw new IllegalArgumentException("없는 댓글 입니다.");
        }
        commentRepository.deleteById(commentId);
    }
}
