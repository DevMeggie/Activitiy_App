package com.devmeggie.week_8.services.serviceImpl;

import com.devmeggie.week_8.dtos.UserLoginDto;
import com.devmeggie.week_8.dtos.UserSignUpDto;
import com.devmeggie.week_8.exceptions.NotFoundException;
import com.devmeggie.week_8.exceptions.UserAlreadyExsitsException;
import com.devmeggie.week_8.models.User;
import com.devmeggie.week_8.repositories.UserRepo;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.*;
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

    UserSignUpDto userSignUpDto= new UserSignUpDto("maggie", "maggie123", "female", "maggie123");

    UserLoginDto userLoginDto = new UserLoginDto("maggie123","maggie123");


    @Test
    void userSignUp() {
        when(testUserRepo.existsByEmail(anyString())).thenReturn(false);
        when(testUserRepo.save(any())).thenReturn(user);
        User result = testUserServiceImpl.userSignUp(userSignUpDto);

        Assertions.assertEquals(user.getFullName(),result.getFullName());
        Assertions.assertEquals(user.getEmail(),result.getEmail());
        Assertions.assertEquals(user.getGender(),result.getGender());
        Assertions.assertEquals(user.getPassword(),result.getPassword());
    }

    @Test
    void shouldThrowNewUserAlreadyExsitsException(){
        when(testUserRepo.existsByEmail(anyString())).thenReturn(true);
        Assertions.assertThrows(UserAlreadyExsitsException.class, ()->testUserServiceImpl.userSignUp(userSignUpDto));

    }


    @Test
    void shouldBeAbleToLogin() {
        when(testUserRepo.existsByEmail(anyString())).thenReturn(true);
        when(testUserRepo.findByEmailAndPassword("maggie123", "maggie123")).thenReturn(user);
        UserLoginDto userLoginDto = new UserLoginDto("maggie123", "maggie123");
        User result = testUserServiceImpl.login(userLoginDto);
        assertEquals(user, result);
    }

//    @Test
//    void shouldThrowNotFoundException(){
//        when(testUserRepo.findByEmailAndPassword(anyString(),anyString())).thenReturn(user);
//        Assertions.assertThrows(NotFoundException.class, ()->testUserServiceImpl.login(userLoginDto));
//    }



    @Test
    void deleteUser() {
        when(httpSession.getAttribute(anyString())).thenReturn(1L);
        when(testUserRepo.existsById(anyLong())).thenReturn(true);
        String result = testUserServiceImpl.deleteUser();
        String expected ="userLogged out";
        assertEquals(expected,result);

    }

}

