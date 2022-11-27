package com.devmeggie.week_8.services.serviceImpl;

import com.devmeggie.week_8.dtos.UserLoginDto;
import com.devmeggie.week_8.dtos.UserSignUpDto;
import com.devmeggie.week_8.models.User;
import com.devmeggie.week_8.repositories.UserRepo;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.BeanUtils;
import org.springframework.validation.annotation.Validated;

import javax.servlet.http.HttpSession;

import java.util.ArrayList;

import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

class UserServiceImplTest {
    @Mock
    UserRepo testUserRepo;

    @Mock
    HttpSession httpSession;

    @InjectMocks
    UserServiceImpl testUserServiceImpl;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    User user = new User("maggie", "maggie123", "female",
            "maggie123", new ArrayList<>());


    UserSignUpDto signUp = new UserSignUpDto("maggie", "maggie123", "female", "maggie123");

    @Disabled
    @Test
    void userSignUp() {
        when(testUserRepo.existsByEmail(anyString())).thenReturn(false);
        User result = testUserServiceImpl.userSignUp(signUp);

        Assertions.assertEquals(user, result);
    }


    @Test
    void ShouldBeAbleToLogin() {
        when(testUserRepo.existsByEmail(anyString())).thenReturn(true);
        when(testUserRepo.findByEmailAndPassword("maggie123", "maggie123")).thenReturn(user);
        UserLoginDto userLoginDto = new UserLoginDto("maggie123", "maggie123");
        User result = testUserServiceImpl.login(userLoginDto);

        Assertions.assertEquals(user, result);
    }



    @Test
    void deleteUser() {
        String result = testUserServiceImpl.deleteUser();
        String expected ="user not found";
        Assertions.assertEquals(expected,result);






    }

}

