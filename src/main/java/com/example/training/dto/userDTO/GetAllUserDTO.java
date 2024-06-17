package com.example.training.dto.userDTO;

import lombok.Data;

import java.time.LocalDate;

@Data
public class GetAllUserDTO {
    private Integer id;
    private String username;
    private String name;
    private int age;
    private String gender;
    private LocalDate dob;
}
