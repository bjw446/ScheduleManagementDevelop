package com.example.schedulemanagementdevelop.schedule.repository;

import com.example.schedulemanagementdevelop.schedule.dto.GetPageResponse;
import com.example.schedulemanagementdevelop.schedule.entity.Schedule;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;


public interface ScheduleRepository extends JpaRepository <Schedule, Long> {

    @Query("SELECT new com.example.schedulemanagementdevelop.schedule.dto.GetPageResponse" +
            "(s.id, s.scheduleTitle, s.scheduleContent, COUNT(c), s.createdAt, s.modifiedAt, u.name) "  +
            "From Schedule s " +
            "LEFT JOIN Comment c On c.schedule = s " +
            "JOIN s.user u " +
            "GROUP BY s.id, s.scheduleTitle, s.scheduleContent, s.createdAt, s.modifiedAt, u.name " +
            "ORDER BY s.modifiedAt DESC")
    Page<GetPageResponse> findAllByOrderByModifiedAtDesc(Pageable pageable);
}
