package com.devmeggie.week_8.dtos;

import com.devmeggie.week_8.enums.TaskStatus;
import lombok.*;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
public class TaskCreatedDto {
    private String title;
    private String description;
    private TaskStatus status;
    private LocalDateTime createdAt;


}
