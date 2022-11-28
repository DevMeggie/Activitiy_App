package com.devmeggie.week_8.services.serviceImpl;

import com.devmeggie.week_8.dtos.TaskCreatedDto;
import com.devmeggie.week_8.dtos.TaskViewDto;
import com.devmeggie.week_8.enums.TaskStatus;
import com.devmeggie.week_8.exceptions.NotFoundException;
import com.devmeggie.week_8.exceptions.TaskDoesntExistException;
import com.devmeggie.week_8.models.Task;
import com.devmeggie.week_8.models.User;
import com.devmeggie.week_8.repositories.TaskRepo;
import com.devmeggie.week_8.repositories.UserRepo;
import com.devmeggie.week_8.services.TaskService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class TaskServiceImpl implements TaskService {
    private final TaskRepo taskRepo;
    private final UserRepo userRepo;
    private final HttpSession httpSession;

    @Override
    public Task CreateTask(TaskCreatedDto taskDto) {
        Long user_id = (Long) httpSession.getAttribute("user_id");
        userRepo.findById(user_id).orElseThrow(() -> new NotFoundException("user doesnt exist"));
        Task task = new Task();
        task.setTitle(taskDto.getTitle());
        task.setDescription(taskDto.getDescription());
        task.setStatus(TaskStatus.PENDING);
        task.setUserId(user_id);
        taskRepo.save(task);
        return task;
    }

    @Override
    public List<Task> viewAllTask() {
        Long user_id = (Long) httpSession.getAttribute("user_id");

        Optional<User> optionalUser = userRepo.findById(user_id);
        if (optionalUser.isPresent())
            return taskRepo.findByUserId(user_id);
        else
            throw new NotFoundException("user doesnt exist!");

    }

    @Override
    public TaskViewDto viewTask(Long task_id) {// noted
        Long user_id = (Long) httpSession.getAttribute("user_id");
        userRepo.findById(user_id)
                .orElseThrow(() -> new NotFoundException("sorry! this task doesnt exist"));

        Optional<Task> taskOptional = taskRepo.findById(task_id);
        Task task = taskOptional.orElseThrow(() -> new TaskDoesntExistException("Task does not exist"));

        boolean isTaskOwner = task.getUserId().equals(user_id);

        if(!isTaskOwner) throw new TaskDoesntExistException("Task does not exist");

        return TaskViewDto.builder()
                .taskId(task.getId())
                .userId(task.getUserId())
                .title(task.getTitle())
                .description(task.getDescription())
                .build();
    }

    @Override
    public List<Task> viewAllPendingTask() {
        Long user_id = (Long) httpSession.getAttribute("user_id");

        userRepo.findById(user_id)
                .orElseThrow(() -> new NotFoundException("sorry! user not found"));

        return taskRepo.findAllByStatus(user_id, TaskStatus.PENDING);


    }

    @Override
    public List<Task> viewCompletedTask() {
        Long user_id = (Long) httpSession.getAttribute("user_id");
        userRepo.findById(user_id)
                .orElseThrow(() -> new NotFoundException("sorry! user not found"));

        return taskRepo.findAllByStatus(user_id, TaskStatus.COMPLETED);
    }

    @Override
    public Task updateTaskById(Long taskId, TaskCreatedDto taskCreatedDto) {
        Long Id = (Long) httpSession.getAttribute("user_id");
        Optional<Task> taskOptional = taskRepo.findById(taskId);

        Task oldTask = taskOptional.orElseThrow(() ->
                new TaskDoesntExistException("Task does not exist"));


        String newTitle = taskCreatedDto.getTitle();
        String newDescription = taskCreatedDto.getDescription();
        TaskStatus newTaskStatus = taskCreatedDto.getStatus();


        if (newTitle != null && !newTitle.isBlank()) {
            oldTask.setTitle(newTitle);
        }
        if (newDescription != null && !newDescription.isBlank()) {
            oldTask.setDescription(newDescription);
        }

        if (newTaskStatus != null && !newTaskStatus.toString().isBlank()) {
            oldTask.setStatus(newTaskStatus);
        }

        return taskRepo.save(oldTask);
    }

    @Override
    public void deleteTask(Long task_id) {
        Long user_id = (Long) httpSession.getAttribute("user_id");
        boolean exists = userRepo.existsById(user_id);
        if (!exists) {
            throw new NotFoundException("user not found");
        } else {
            taskRepo.findById(task_id);
        }
    }

    @Override
    public List<Task> viewAllInProgressTask(TaskViewDto t) {
        Long user_id = (Long) httpSession.getAttribute("user_id");

        userRepo.findById(user_id).orElseThrow(() -> new NotFoundException("sorry user not found"));

        return taskRepo.findAllByStatus(user_id, TaskStatus.IN_PROGRESS);
    }
}






