package com.example.sweet_shop.service;

import com.example.sweet_shop.model.User;
import com.example.sweet_shop.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor // Lombok annotation for constructor injection
public class AuthService {

    // Dependencies injected by Spring
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public User register(String username, String password) {
        // Check if user already exists
        if (userRepository.findByUsername(username).isPresent()) {
            throw new RuntimeException("Username already exists");
        }

        User newUser = new User();
        newUser.setUsername(username);
        // Hash the password before saving
        newUser.setPassword(passwordEncoder.encode(password));
        newUser.setRole("USER");

        // Save the user to the database and return the saved entity
        return userRepository.save(newUser);
    }
}