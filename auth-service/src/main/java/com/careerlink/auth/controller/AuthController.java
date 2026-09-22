package com.careerlink.auth.controller;

import com.careerlink.auth.dto.AuthResponse;
import com.careerlink.auth.entity.User;
import com.careerlink.auth.service.AuthService;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private final AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping("/register")
    public User register(@RequestBody User user) {
        return authService.register(user);
    }

    @PostMapping("/login")
    public AuthResponse login(@RequestBody User user) {

        return authService.login(
                user.getEmail(),
                user.getPassword()
        );
    }
}