package com.example.sweetshop.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

// Import statements for User and AuthService will show errors
import com.example.sweetshop.model.User;

@SpringBootTest
class AuthServiceTest {

    // This will show an error because AuthService doesn't exist yet
    @Autowired
    private AuthService authService;

    @Test
    void whenRegisterUser_thenUserShouldBeCreated() {
        // Act: We try to use a method that doesn't exist on a class that doesn't exist
        User registeredUser = authService.register("testuser", "password123");

        // Assert: We check the expected outcome
        assertNotNull(registeredUser);
        assertEquals("testuser", registeredUser.getUsername());
    }
}