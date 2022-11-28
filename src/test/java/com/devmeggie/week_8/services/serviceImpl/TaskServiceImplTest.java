package com.devmeggie.week_8.services.serviceImpl;

import com.devmeggie.week_8.dtos.TaskCreatedDto;
import com.devmeggie.week_8.enums.TaskStatus;
import com.devmeggie.week_8.exceptions.NotFoundException;
import com.devmeggie.week_8.models.Task;
import com.devmeggie.week_8.models.User;
import com.devmeggie.week_8.repositories.TaskRepo;
import com.devmeggie.week_8.repositories.UserRepo;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;

public class TaskServiceImplTest {
    @Mock
    TaskRepo testTaskRepo;

    @Mock
    UserRepo userRepo;

    @Mock
    HttpSession httpSession;

    @InjectMocks
    TaskServiceImpl testTaskServiceImpl;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    User user = new User("maggie", "maggie123", "female", "maggie123", new ArrayList<>());

    Task task2 = Task.builder()
            .title("hello")
            .userId(2L)
            .description("im saying hello to you")
            .status(TaskStatus.PENDING)
            .build();

    Task duplicatetask = new Task("hello", "im saying hello to you", user.getId(), TaskStatus.PENDING, null);

    TaskCreatedDto taskCreatedDto = new TaskCreatedDto("hello", "im saying hello to you", TaskStatus.PENDING, null);


    @Test
    void createTask() {
        httpSession.setAttribute("user_id", 2L);
        when((Long) httpSession.getAttribute("user_id")).thenReturn(2L);
        when(userRepo.findById(2L)).thenReturn(Optional.of(user));
        when(testTaskRepo.save(task2)).thenReturn(task2);
        Task result = testTaskServiceImpl.CreateTask(taskCreatedDto);
        Assertions.assertEquals(task2.getTitle(), result.getTitle());
        Assertions.assertEquals(task2.getDescription(), result.getDescription());
        Assertions.assertEquals(task2.getUserId(), result.getUserId());
        Assertions.assertEquals(task2.getStatus(), result.getStatus());
    }
    @Test
    void shouldThrowNotFoundException(){
        when(userRepo.findById(anyLong())).thenReturn(Optional.of(user));
        Assertions.assertThrows(NotFoundException.class,()->testTaskServiceImpl.CreateTask(taskCreatedDto));
    }
    
    @Test
    void viewAllTask() {
        httpSession.setAttribute("user_id", 2L);
        when((Long) httpSession.getAttribute("user_id")).thenReturn(2L);
        when(userRepo.findById(anyLong())).thenReturn(Optional.of(user));
        when(testTaskRepo.findByUserId(anyLong())).thenReturn(List.of(task2));
        List<Task>tasks = testTaskServiceImpl.viewAllTask();
        Assertions.assertEquals(List.of(task2),tasks);
   }
   @Test
    void ShouldThrowNotFoundException(){
           when(userRepo.findById(anyLong())).thenReturn(Optional.of(user));
           Assertions.assertThrows(NotFoundException.class,()->testTaskServiceImpl.CreateTask(taskCreatedDto));
       }
   }


//}
//
//    @Test
//    void viewTask() {
//    }
//
//    @Test
//    void viewAllPendingTask() {
//    }
//
//    @Test
//    void viewCompletedTask() {
//    }
//
//    @Test
//    void updateTaskById() {
//    }
//
//    @Test
//    void deleteTask() {
//    }
//
//    @Test
//    void viewAllInProgressTask() {
//    }
//}
