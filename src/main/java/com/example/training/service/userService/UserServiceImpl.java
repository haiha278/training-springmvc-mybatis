package com.example.training.service.userService;

import com.example.training.dto.userDTO.GetAllUserDTO;
import com.example.training.dto.userDTO.LoginDTO;
import com.example.training.dto.userDTO.RegisterDTO;
import com.example.training.entity.User;
import com.example.training.modelMapper.Mapper;
import com.example.training.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {
    private UserRepository userRepository;
    private Mapper mapper;

    @Autowired
    public UserServiceImpl(UserRepository userRepository, Mapper mapper) {
        this.userRepository = userRepository;
        this.mapper = mapper;
    }

    @Override
    public List<GetAllUserDTO> getAllUsers() {
        List<GetAllUserDTO> userList = userRepository.findAll().stream().map(user -> {
            GetAllUserDTO getAllUserDTO = (GetAllUserDTO) mapper.mapToDTO(user, GetAllUserDTO.class);
//            GetAllUserDTO getAllUserDTO = new GetAllUserDTO();
//            getAllUserDTO.setId(user.getId());
//            getAllUserDTO.setName(user.getName());
//            getAllUserDTO.setAge(user.getAge());
//            getAllUserDTO.setGender(user.getGender());
//            getAllUserDTO.setUsername(user.getUsername());
//            LocalDate localDate = user.getDob();
//            getAllUserDTO.setDateOfBirth(localDate);
            return getAllUserDTO;
        }).toList();
        return userList;
    }

    @Override
    public LoginDTO login(String username) {
        Optional optionalLoginDTO = userRepository.findByUsername(username);
        if (optionalLoginDTO.isPresent()) {
            User user = (User) optionalLoginDTO.get();
            LoginDTO loginDTO = new LoginDTO();
            loginDTO.setUsername(user.getUsername());
            loginDTO.setPassword(user.getPassword());
            return loginDTO;
        }
        return null;
    }

    @Override
    public boolean registerUser(RegisterDTO registerDTO) {
        if (userRepository.findByUsername(registerDTO.getUsername()).isPresent()) {
            return false;
        }
        User user = (User) mapper.mapToEntity(registerDTO, User.class);
        int rowAffected = userRepository.insert(user);
        if (rowAffected > 0) {
            return true;
        }
        return false;
    }
}
