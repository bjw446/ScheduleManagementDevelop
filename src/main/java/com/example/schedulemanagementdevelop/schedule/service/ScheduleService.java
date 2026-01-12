package com.example.schedulemanagementdevelop.schedule.service;

import com.example.schedulemanagementdevelop.comment.dto.GetCommentResponse;
import com.example.schedulemanagementdevelop.schedule.dto.GetPageResponse;
import com.example.schedulemanagementdevelop.comment.entity.Comment;
import com.example.schedulemanagementdevelop.comment.repository.CommentRepository;
import com.example.schedulemanagementdevelop.schedule.dto.*;
import com.example.schedulemanagementdevelop.schedule.entity.Schedule;
import com.example.schedulemanagementdevelop.schedule.repository.ScheduleRepository;
import com.example.schedulemanagementdevelop.user.entity.User;
import com.example.schedulemanagementdevelop.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ScheduleService {
    private final ScheduleRepository scheduleRepository;
    private final UserRepository userRepository;
    private final CommentRepository commentRepository;

    @Transactional
    public CreateScheduleResponse save(CreateScheduleRequest request) {
        User user = userRepository.findById(request.getUserId()).orElseThrow(
                () -> new IllegalArgumentException("없는 유저 입니다.")
        );

        Schedule schedule = new Schedule(
                request.getScheduleTitle(),
                request.getScheduleContent(),
                user
        );

        Schedule savedSchedule = scheduleRepository.save(schedule);
        return new CreateScheduleResponse(
                savedSchedule.getId(),
                savedSchedule.getScheduleTitle(),
                savedSchedule.getScheduleContent(),
                user.getId(),
                savedSchedule.getCreatedAt(),
                savedSchedule.getModifiedAt()
        );
    }

    @Transactional(readOnly = true)
    public List<GetScheduleResponse> findAllSchedule() {
        List<Schedule> schedules = scheduleRepository.findAll();
        List<GetScheduleResponse> dtos = new ArrayList<>();

        for (Schedule schedule : schedules) {
            GetScheduleResponse dto = new GetScheduleResponse(
                    schedule.getId(),
                    schedule.getScheduleTitle(),
                    schedule.getScheduleContent(),
                    schedule.getModifiedAt()
            );
            dtos.add(dto);
        }
        return dtos;
    }

    @Transactional(readOnly = true)
    public GetScheduleAndCommentResponse findOneSchedule(Long scheduleId) {
        Schedule schedule = scheduleRepository.findById(scheduleId).orElseThrow(
                () -> new IllegalArgumentException("없는 일정 입니다.")
        );
        // 스케줄ID를 기준으로 댓글 리스트 구현
        List<Comment> comments = commentRepository.findAllByScheduleId(scheduleId);
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
        return new GetScheduleAndCommentResponse(
                schedule.getId(),
                schedule.getScheduleTitle(),
                schedule.getScheduleContent(),
                schedule.getModifiedAt(),
                dtos
        );
    }

    @Transactional
    public UpdateScheduleResponse updateSchedule(Long scheduleId, UpdateScheduleRequest request) {
        Schedule schedule = scheduleRepository.findById(scheduleId).orElseThrow(
                () -> new IllegalArgumentException("없는 일정 입니다.")
        );

        schedule.update(request.getScheduleTitle(), request.getScheduleContent(), schedule.getUser());
        return new UpdateScheduleResponse(
                schedule.getId(),
                schedule.getScheduleTitle(),
                schedule.getScheduleContent(),
                schedule.getModifiedAt()
        );
    }

    @Transactional
    public void delete(Long scheduleId) {
        boolean existence = scheduleRepository.existsById(scheduleId);

        if (!existence) {
            throw new IllegalArgumentException("없는 일정 입니다.");
        }

        // 댓글이 등록된 일정 삭제시 등록된 댓글도 같이 삭제
        commentRepository.deleteAllByScheduleId(scheduleId);
        scheduleRepository.deleteById(scheduleId);
    }

    @Transactional(readOnly = true)
    public Page<GetPageResponse> findAllSchedulePage(Pageable pageable) {
        return scheduleRepository.findAllByOrderByModifiedAtDesc(pageable);
    }
}
