package com.example.sweet_shop.service;

import com.example.sweet_shop.model.User;
import org.springframework.stereotype.Service;

@Service // Marks this class as a Spring service component
public class AuthService {

    /**
     * This is a temporary implementation to make our test pass.
     * We will add real logic and database interaction in the next step.
     */
    public User register(String username, String password) {
        User newUser = new User();
        newUser.setUsername(username);
        newUser.setPassword(password); // Note: Password is not yet hashed
        newUser.setRole("USER");
        return newUser;
    }
}