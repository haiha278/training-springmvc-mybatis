package com.example.training.controller;

import com.example.training.dto.userDTO.GetAllUserDTO;
import com.example.training.dto.userDTO.LoginDTO;
import com.example.training.dto.userDTO.RegisterDTO;
import com.example.training.service.userService.UserService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@org.springframework.stereotype.Controller
public class UserController {
    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/user-list")
    public String userList(Model model) {
        List<GetAllUserDTO> getAllUserDTOList = userService.getAllUsers();
        model.addAttribute("allUsers", getAllUserDTOList);
        return "user-list";
    }
}
