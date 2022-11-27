package com.devmeggie.week_8.services;

import com.devmeggie.week_8.dtos.UserLoginDto;
import com.devmeggie.week_8.dtos.UserSignUpDto;
import com.devmeggie.week_8.models.User;

public interface UserService {
    User userSignUp(UserSignUpDto userSignUpDto);

    User login(UserLoginDto userLoginDto);

    void deleteUser();


}

