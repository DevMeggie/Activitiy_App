package com.devmeggie.week_8.services.serviceImpl;

import com.devmeggie.week_8.dtos.TaskCreatedDto;
import com.devmeggie.week_8.dtos.UserSignUpDto;
import com.devmeggie.week_8.enums.TaskStatus;
import com.devmeggie.week_8.models.Task;
import com.devmeggie.week_8.models.User;
import com.devmeggie.week_8.repositories.TaskRepo;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import java.util.ArrayList;

import static org.mockito.Mockito.when;

public class TaskServiceImplTest {
    @Mock
    TaskRepo testTaskRepo;

    @InjectMocks
    TaskServiceImpl testTaskServiceImpl;

    @BeforeEach
    void setUp(){

    }
    User user = new User("maggie","maggie123","female","maggie123",new ArrayList<>());

    //Task task = new Task("hello","im saying hello to you", user.getId(), TaskStatus.IN_PROGRESS,null);

    Task duplicatetask = new Task("hello","im saying hello to you", user.getId(), TaskStatus.PENDING,null);
    TaskCreatedDto taskCreatedDto = new TaskCreatedDto("hello","im saying hello to you",TaskStatus.PENDING,null);


    @Test
    void createTask() {
        when("");
    }

    @Test
    void viewAllTask() {
    }

    @Test
    void viewTask() {
    }

    @Test
    void viewAllPendingTask() {
    }

    @Test
    void viewCompletedTask() {
    }

    @Test
    void updateTaskById() {
    }

    @Test
    void deleteTask() {
    }

    @Test
    void viewAllInProgressTask() {
    }
}
