package com.example.sweet_shop.controller;

import com.example.sweet_shop.model.User;
import com.example.sweet_shop.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/admin")
@PreAuthorize("hasRole('ADMIN')")
public class AdminController {

    private final UserService userService;

    public AdminController(UserService userService) {
        this.userService = userService;
    }

    // List all users
    @GetMapping("/users")
    public ResponseEntity<List<User>> getAllUsers() {
        return ResponseEntity.ok(userService.getAllUsers());
    }

    // Promote a user to admin
    @PatchMapping("/users/{id}/promote")
    public ResponseEntity<User> promoteUser(@PathVariable Long id) {
        User updated = userService.promoteToAdmin(id);
        return ResponseEntity.ok(updated);
    }
}

