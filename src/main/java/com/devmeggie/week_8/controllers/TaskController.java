package com.devmeggie.week_8.controllers;

import com.devmeggie.week_8.dtos.TaskCreatedDto;
import com.devmeggie.week_8.dtos.TaskViewDto;
import com.devmeggie.week_8.models.Task;
import com.devmeggie.week_8.services.TaskService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.List;

@RequestMapping("/task")
@RestController
@RequiredArgsConstructor

public class TaskController {
    private final TaskService taskService;
    private final HttpSession httpSession;


    @PostMapping("/createTask")
    public String createTask(@RequestBody
                                 TaskCreatedDto taskDto) {
        taskService.CreateTask(taskDto);
        httpSession.getAttribute("user_id");
        return "Task successfully created";
    }

    @GetMapping("/viewAllTask")
    public List<Task> viewAllTask() {
        //httpSession.getAttribute("user_id");
        return taskService.viewAllTask();
    }

    @GetMapping("/{taskId}")
    public TaskViewDto viewTask(@PathVariable Long taskId) {
        taskService.viewTask(taskId);
        httpSession.getAttribute("user_id");
        return taskService.viewTask(taskId);

    }

    @GetMapping("/pending-task")
    public List<Task> viewAllPendingTask() {
        // httpSession.getAttribute("user_id");
        return taskService.viewAllPendingTask();
    }

    @GetMapping("/completed-task")
    public List<Task> viewCompletedTask() {
        httpSession.getAttribute("user_id");
        return taskService.viewCompletedTask();
    }

    @PutMapping("/{taskId}/updateTask")
    public Task updateTask(@PathVariable Long taskId, @RequestBody TaskCreatedDto taskCreatedDto) {
        return taskService.updateTaskById(taskId, taskCreatedDto);
    }

    @DeleteMapping("/{task_id}/deleteTask")
    public ResponseEntity<String> deleteTask(@PathVariable Long task_id){
        taskService.deleteTask(task_id);
        return new ResponseEntity<>("task deleted successfully", HttpStatus.NO_CONTENT);
    }

    @GetMapping("/In-Progress")
    public List<Task>viewAllInProgressTask(TaskViewDto taskViewDto){

        return taskService.viewAllInProgressTask(taskViewDto);
    }

}




