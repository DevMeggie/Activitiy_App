package com.devmeggie.week_8.services.serviceImpl;

import com.devmeggie.week_8.dtos.UserLoginDto;
import com.devmeggie.week_8.dtos.UserSignUpDto;
import com.devmeggie.week_8.exceptions.NotFoundException;
import com.devmeggie.week_8.exceptions.UserAlreadyExsitsException;
import com.devmeggie.week_8.models.User;
import com.devmeggie.week_8.repositories.TaskRepo;
import com.devmeggie.week_8.repositories.UserRepo;
import com.devmeggie.week_8.services.UserService;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;

@Service
public class UserServiceImpl implements UserService {
    private final UserRepo userRepo;
    private final TaskRepo taskRepo;
    private final HttpSession httpSession;

    public UserServiceImpl(UserRepo userRepo, TaskRepo taskRepo, HttpSession httpSession) {
        this.userRepo = userRepo;
        this.taskRepo = taskRepo;
        this.httpSession = httpSession;
    }

    @Override
    public User userSignUp(UserSignUpDto userSignUpDto) {
        String userEmail = userSignUpDto.getEmail();
        boolean userExist = userRepo.existsByEmail(userEmail);
        if (userExist)
            throw new UserAlreadyExsitsException(userEmail);
        User user = new User();
        user.setFullName(userSignUpDto.getFullName());
        user.setEmail(userSignUpDto.getEmail());
        user.setGender(userSignUpDto.getGender());
        user.setPassword(userSignUpDto.getPassword());
        user.setTaskList(new ArrayList<>());
        userRepo.save(user);
        return user;
    }

    @Override
    public User login(UserLoginDto userLoginDto) {

        if(userRepo.findByEmailAndPassword(userLoginDto.getEmail(), userLoginDto.getPassword())!=null){
          User  user = userRepo.findByEmailAndPassword(userLoginDto.getEmail(), userLoginDto.getPassword());
            return user;
        }
        throw new NotFoundException("User not Found or wrong email and password");

    }

    @Override
    public String deleteUser() {
      Long user_id = (Long) httpSession.getAttribute("user_id");
        boolean exists= userRepo.existsById(user_id);
        if(!exists){
            throw new NotFoundException("user not found");
        }
        userRepo.deleteById(user_id);
        return "userLogged out";
    }

    }















