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

    @GetMapping("/login")
    public String login() {
        return "index";
    }

    @PostMapping("/login")
    public String login(@ModelAttribute("loginDTO") LoginDTO loginDTO, HttpSession session) {
        LoginDTO loginResponse = userService.login(loginDTO.getUsername());
        if (loginResponse != null && loginResponse.getPassword().equals(loginDTO.getPassword())) {
            session.setAttribute("username", loginResponse.getUsername());
            return "redirect:/user-list";
        }
        return "login";
    }

    @GetMapping("/register")
    public String register() {
        return "register";
    }

    @PostMapping("/register")
    public String register(@ModelAttribute("registerDTO") RegisterDTO registerDTO, Model model) {
        boolean registerUserResult = userService.registerUser(registerDTO);
        if (registerUserResult) {
            return "redirect:/login";
        }else {
            String errorMessage = "Register user failed, please try again";
            model.addAttribute("errorMessage", errorMessage);
            return "register";
        }
    }


    @GetMapping("/user-list")
    public String userList(Model model) {
        List<GetAllUserDTO> getAllUserDTOList = userService.getAllUsers();
        model.addAttribute("allUsers", getAllUserDTOList);
        return "user-list";
    }
}
