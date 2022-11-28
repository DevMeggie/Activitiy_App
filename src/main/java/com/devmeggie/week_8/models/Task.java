package com.devmeggie.week_8.models;

import com.devmeggie.week_8.enums.TaskStatus;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Builder
@ToString
public class Task extends BaseClass{
    @Column(nullable = false, length = 50)
    private String title;

    @Column(nullable = false, length = 100)
    private String description;

    @Column(nullable = false, length = 20)
    private Long userId;

    @Enumerated(EnumType.STRING)
    private TaskStatus status;
    private LocalDateTime completedAt;

    @PreUpdate
    public void setUpdate() {
        if(status.equals(TaskStatus.COMPLETED)) {
            completedAt = LocalDateTime.now();
        }

    }




}
