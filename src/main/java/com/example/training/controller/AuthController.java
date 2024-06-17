package com.example.training.controller;

import com.example.training.dto.userDTO.LoginDTO;
import com.example.training.dto.userDTO.RegisterDTO;
import com.example.training.security.CustomUserDetails;
import com.example.training.security.JwtTokenProvider;
import com.example.training.service.userService.UserService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/auth")
public class AuthController {
    private final UserService userService;

    @Autowired
    private AuthenticationManager authenticationManager;
    private JwtTokenProvider jwtTokenProvider;

    @Autowired
    public AuthController(UserService userService, JwtTokenProvider jwtTokenProvider) {
        this.userService = userService;
        this.jwtTokenProvider = jwtTokenProvider;
    }


//    @GetMapping("/login")
//    public String login() {
//        return "index";
//    }
//
//    @PostMapping("/login")
//    public String login(@ModelAttribute("loginDTO") LoginDTO loginDTO, HttpSession session) {
//        LoginDTO loginResponse = userService.login(loginDTO.getUsername());
//        if (loginResponse != null && loginResponse.getPassword().equals(loginDTO.getPassword())) {
//            session.setAttribute("username", loginResponse.getUsername());
//            return "redirect:/user-list";
//        }
//        return "index";
//    }

    @GetMapping("/login")
    public String login(Model model) {
        model.addAttribute("loginDTO", new LoginDTO());
        return "index"; // Trả về file index.jsp
    }

    @PostMapping("/login")
    @ResponseBody
    public ResponseEntity<?> login(@ModelAttribute("loginDTO") LoginDTO loginDTO, Model model) {
        LoginDTO loginResponse = userService.login(loginDTO.getUsername());
        if (loginResponse != null && loginResponse.getPassword().equals(loginDTO.getPassword())) {
            Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginDTO.getUsername(), loginDTO.getPassword()));
            SecurityContextHolder.getContext().setAuthentication(authentication);
            CustomUserDetails customUserDetails = (CustomUserDetails) authentication.getPrincipal();
            String token = jwtTokenProvider.generateToken(customUserDetails);
            return ResponseEntity.ok(token);
        }
        model.addAttribute("loginError", "Invalid username or password");
        return ResponseEntity.status(401).body("Invalid username or password");
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
        } else {
            String errorMessage = "Register user failed, please try again";
            model.addAttribute("errorMessage", errorMessage);
            return "register";
        }
    }
}
