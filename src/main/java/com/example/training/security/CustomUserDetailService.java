package com.example.training.security;

import com.example.training.entity.User;
import com.example.training.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CustomUserDetailService implements UserDetailsService {
    private UserRepository userRepository;

    @Autowired
    public CustomUserDetailService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional optionalUser = userRepository.findByUsername(username);
        if (optionalUser.isPresent()) {
            return CustomUserDetails.mapUserToCustomerUserDetails((User) optionalUser.get());
        }
        throw new UsernameNotFoundException("not found user");
    }
}
