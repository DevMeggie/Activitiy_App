package com.devmeggie.week_8.repositories;

import com.devmeggie.week_8.enums.TaskStatus;
import com.devmeggie.week_8.models.Task;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface TaskRepo extends JpaRepository<Task,Long> {

//    List<Task> findById(TaskCreatedDto taskDto);

    @Query( "SELECT u from Task u where  u.userId=?1")
    List <Task> findByUserId(Long user_Id);


    List<Task> findAllByStatus(TaskStatus status);


}
