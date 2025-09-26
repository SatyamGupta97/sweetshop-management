package com.example.sweet_shop.controller;

import com.example.sweet_shop.dto.AuthResponse;
import com.example.sweet_shop.dto.RegisterRequest;
import com.example.sweet_shop.service.AuthService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    @PostMapping("/register")
    public ResponseEntity<AuthResponse> registerUser(@Valid @RequestBody RegisterRequest registerRequest) {
        authService.register(registerRequest.getUsername(), registerRequest.getPassword());
        return ResponseEntity.ok(new AuthResponse("User registered successfully!"));
    }
}