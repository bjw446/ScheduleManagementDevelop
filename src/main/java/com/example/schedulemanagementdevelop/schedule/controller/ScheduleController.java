package com.example.schedulemanagementdevelop.schedule.controller;

import com.example.schedulemanagementdevelop.schedule.dto.GetPageResponse;
import com.example.schedulemanagementdevelop.schedule.dto.*;
import com.example.schedulemanagementdevelop.schedule.service.ScheduleService;
import com.example.schedulemanagementdevelop.user.dto.SessionUser;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequiredArgsConstructor
public class ScheduleController {
    private final ScheduleService scheduleService;

    @PostMapping("/schedules") // SessionAttribute 로 로그인 여부 확인하기
    public ResponseEntity<CreateScheduleResponse> createSchedule (@Valid @SessionAttribute(name = "loginUser", required = false) SessionUser sessionUser, @RequestBody CreateScheduleRequest request) {
        if (sessionUser == null) {
            throw new IllegalStateException("로그인이 필요한 서비스 입니다.");
        }
        return ResponseEntity.status(HttpStatus.CREATED).body(scheduleService.save(request));
    }

    @GetMapping("/schedules") // SessionAttribute 로 로그인 여부 확인하기
    public ResponseEntity<List<GetScheduleResponse>> getAllSchedule (@Valid @SessionAttribute(name = "loginUser", required = false) SessionUser sessionUser) {
        if (sessionUser == null) {
            throw new IllegalStateException("로그인이 필요한 서비스 입니다.");
        }
        return ResponseEntity.status(HttpStatus.OK).body(scheduleService.findAllSchedule());
    }

    @GetMapping("/schedules/page") // SessionAttribute 로 로그인 여부 확인하기
    public ResponseEntity<Page<GetPageResponse>> getAllSchedulePage (@Valid @SessionAttribute(name = "loginUser", required = false) SessionUser sessionUser, @PageableDefault(size = 10) Pageable pageable) {
        if (sessionUser == null) {
            throw new IllegalStateException("로그인이 필요한 서비스 입니다.");
        }
        return ResponseEntity.status(HttpStatus.OK).body(scheduleService.findAllSchedulePage(pageable));
    }

    @GetMapping("/schedules/{scheduleId}") // SessionAttribute 로 로그인 여부 확인하기
    public ResponseEntity<GetScheduleAndCommentResponse> getOneSchedule(@Valid @SessionAttribute(name = "loginUser", required = false) SessionUser sessionUser, @PathVariable Long scheduleId) {
        if (sessionUser == null) {
            throw new IllegalStateException("로그인이 필요한 서비스 입니다.");
        }
        return ResponseEntity.status(HttpStatus.OK).body(scheduleService.findOneSchedule(scheduleId));
    }

    @PutMapping("/schedules/{scheduleId}") // SessionAttribute 로 로그인 여부 확인하기
    public ResponseEntity<UpdateScheduleResponse> updateSchedule(@Valid @SessionAttribute(name = "loginUser", required = false) SessionUser sessionUser, @PathVariable Long scheduleId, @RequestBody UpdateScheduleRequest request) {
        if (sessionUser == null) {
            throw new IllegalStateException("로그인이 필요한 서비스 입니다.");
        }
        return ResponseEntity.status(HttpStatus.OK).body(scheduleService.updateSchedule(scheduleId, request));
    }

    @DeleteMapping("/schedules/{scheduleId}") // SessionAttribute 로 로그인 여부 확인하기
    public ResponseEntity<Void> deleteSchedule (@Valid @SessionAttribute(name = "loginUser", required = false) SessionUser sessionUser, @PathVariable Long scheduleId) {
        if (sessionUser == null) {
            throw new IllegalStateException("로그인이 필요한 서비스 입니다.");
        }
        scheduleService.delete(scheduleId);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
