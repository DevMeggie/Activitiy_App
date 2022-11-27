package com.devmeggie.week_8.services;

import com.devmeggie.week_8.dtos.TaskCreatedDto;
import com.devmeggie.week_8.dtos.TaskViewDto;
import com.devmeggie.week_8.models.Task;

import java.util.List;

public interface TaskService {
    Task CreateTask(TaskCreatedDto taskDto);

    List<Task> viewAllTask();

    TaskViewDto viewTask(Long taskId);

    List<Task> viewAllPendingTask();

    List<Task> viewCompletedTask();

    Task updateTaskById(Long taskId, TaskCreatedDto taskCreatedDto);

    void deleteTask(Long task_id);

    List<Task> viewAllInProgressTask(TaskViewDto taskViewDto);



}
