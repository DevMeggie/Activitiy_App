package com.devmeggie.week_8.dtos;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserSignUpDto {
    private String fullName;
    private String email;
    private String gender;
    private String password;
}
