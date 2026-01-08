package com.example.schedulemanagementdevelop.schedule.service;

import com.example.schedulemanagementdevelop.schedule.dto.*;
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
public class ScheduleService {
    private final ScheduleRepository scheduleRepository;
    private final UserRepository userRepository;

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
    public GetScheduleResponse findOneSchedule(Long scheduleId) {
        Schedule schedule = scheduleRepository.findById(scheduleId).orElseThrow(
                () -> new IllegalArgumentException("없는 일정 입니다.")
        );

        return new GetScheduleResponse(
                schedule.getId(),
                schedule.getScheduleTitle(),
                schedule.getScheduleContent(),
                schedule.getModifiedAt()
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
        scheduleRepository.deleteById(scheduleId);
    }
}
