package com.devmeggie.week_8.controllers;

import com.devmeggie.week_8.dtos.UserLoginDto;
import com.devmeggie.week_8.dtos.UserSignUpDto;
import com.devmeggie.week_8.models.User;
import com.devmeggie.week_8.services.UserService;
import lombok.EqualsAndHashCode;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;

@RequestMapping("/user")
@RestController
@RequiredArgsConstructor
@EqualsAndHashCode
public class UserController {
    private final UserService userService;
    private final HttpSession httpSession;

    @PostMapping("/signup")
    public ResponseEntity<String> signUp(@RequestBody UserSignUpDto signUpDto) {
        userService.userSignUp(signUpDto);
        return new ResponseEntity<>("signUp successfully", HttpStatus.CREATED);
    }

    @PostMapping("/logIn")
    public User logIn(@Validated @RequestBody UserLoginDto userLoginDto) {
       User user = userService.login(userLoginDto);
        httpSession.setAttribute("user_id", user.getId());

     return user;
    }


    @DeleteMapping("/deleteUser")
    public ResponseEntity<String> deleteUser(){
        userService.deleteUser();
        return new ResponseEntity<>("user deleted successfully",HttpStatus.NO_CONTENT);
    }

}































