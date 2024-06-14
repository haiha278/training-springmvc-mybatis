package com.example.training.service.userService;

import com.example.training.dto.userDTO.GetAllUserDTO;
import com.example.training.dto.userDTO.LoginDTO;
import com.example.training.dto.userDTO.RegisterDTO;

import java.util.List;
import java.util.Optional;

public interface UserService {
    List<GetAllUserDTO> getAllUsers();

    LoginDTO login(String username);

    boolean registerUser(RegisterDTO registerDTO);
}
