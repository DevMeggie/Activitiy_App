package com.devmeggie.week_8.dtos;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class TaskViewDto {
    private Long taskId;
    private Long userId;
    private String title;
    private String description;
    //private LocalDateTime completedAt;
}
