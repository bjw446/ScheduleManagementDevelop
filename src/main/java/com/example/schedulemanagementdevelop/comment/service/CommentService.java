package com.example.schedulemanagementdevelop.comment.service;

import com.example.schedulemanagementdevelop.comment.dto.CreateCommentRequest;
import com.example.schedulemanagementdevelop.comment.dto.CreateCommentResponse;
import com.example.schedulemanagementdevelop.comment.dto.GetCommentResponse;
import com.example.schedulemanagementdevelop.comment.entity.Comment;
import com.example.schedulemanagementdevelop.comment.repository.CommentRepository;
import com.example.schedulemanagementdevelop.schedule.entity.Schedule;
import com.example.schedulemanagementdevelop.schedule.repository.ScheduleRepository;
import com.example.schedulemanagementdevelop.user.entity.User;
import com.example.schedulemanagementdevelop.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;

import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CommentService {
    private final CommentRepository commentRepository;
    private final ScheduleRepository scheduleRepository;
    private final UserRepository userRepository;

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
}
