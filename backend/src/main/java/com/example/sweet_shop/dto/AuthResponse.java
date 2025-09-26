package com.example.sweet_shop.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AuthResponse {
    private String message;
    private String token;

    // Constructor for registration response
    public AuthResponse(String message) {
        this.message = message;
    }

    // Constructor for login response
    public AuthResponse(String message, String token) {
        this.message = message;
        this.token = token;
    }
}