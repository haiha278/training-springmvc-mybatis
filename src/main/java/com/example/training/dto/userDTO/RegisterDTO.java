package com.example.training.dto.userDTO;

import lombok.Data;

import java.time.LocalDate;

@Data
public class RegisterDTO {
    private String username;
    private String password;
    private String name;
    private String gender;
    private LocalDate dob;
}
