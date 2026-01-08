package com.example.schedulemanagementdevelop.schedule.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;

@Getter
public class UpdateScheduleRequest {

    @NotBlank(message = "일정 제목은 필수 입력 사항입니다.")
    @Size(min = 2, max = 10, message = "제목은 {min}자 이상 {max}자 이하 이어야 합니다")
    private String scheduleTitle;

    @Size(max = 100, message = "내용은 {max}자 이하 이어야 합니다")
    private String scheduleContent;
}
