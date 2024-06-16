package com.example.training.entity;

import lombok.Data;

import java.time.LocalDate;

@Data
public class User {
    private int id;
    private String username;
    private String password;
    private String name;
    private int age;
    private String gender;
    private LocalDate dob;
    private Role role;
}
