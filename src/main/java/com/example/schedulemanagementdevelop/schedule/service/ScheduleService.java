package com.example.schedulemanagementdevelop.schedule.service;

import com.example.schedulemanagementdevelop.schedule.dto.CreateScheduleResponse;
import com.example.schedulemanagementdevelop.schedule.dto.CreateScheduleRequest;
import com.example.schedulemanagementdevelop.schedule.dto.GetScheduleResponse;
import com.example.schedulemanagementdevelop.schedule.dto.UpdateScheduleResponse;
import com.example.schedulemanagementdevelop.schedule.entity.Schedule;
import com.example.schedulemanagementdevelop.schedule.repository.ScheduleRepository;
import lombok.RequiredArgsConstructor;
import org.jspecify.annotations.Nullable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ScheduleService {
    private final ScheduleRepository scheduleRepository;

    @Transactional
    public CreateScheduleResponse save(CreateScheduleRequest request) {
        Schedule schedule = new Schedule(
                request.getUserName(),
                request.getScheduleTitle(),
                request.getScheduleContent());

        Schedule savedSchedule = scheduleRepository.save(schedule);
        return new CreateScheduleResponse(
                savedSchedule.getId(),
                savedSchedule.getUserName(),
                savedSchedule.getScheduleTitle(),
                savedSchedule.getScheduleContent(),
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
                    schedule.getUserName(),
                    schedule.getScheduleTitle(),
                    schedule.getScheduleContent(),
                    schedule.getCreatedAt(),
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
                schedule.getUserName(),
                schedule.getScheduleTitle(),
                schedule.getScheduleContent(),
                schedule.getCreatedAt(),
                schedule.getModifiedAt()
        );
    }

    @Transactional
    public UpdateScheduleResponse updateSchedule(Long scheduleId) {
        Schedule schedule = scheduleRepository.findById(scheduleId).orElseThrow(
                () -> new IllegalArgumentException("없는 일정 입니다.")
        );

        schedule.update(schedule.getUserName(), schedule.getScheduleTitle(), schedule.getScheduleContent());

        return new UpdateScheduleResponse(
                schedule.getId(),
                schedule.getUserName(),
                schedule.getScheduleTitle(),
                schedule.getScheduleContent(),
                schedule.getCreatedAt(),
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
