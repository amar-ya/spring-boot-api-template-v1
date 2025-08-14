package com.example.NewProject.auth.controller;

import com.example.NewProject.auth.DTOs.AuthResponse;
import com.example.NewProject.auth.DTOs.LoginRequest;
import com.example.NewProject.auth.DTOs.SignupRequest;
import com.example.NewProject.auth.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController
{

    private final AuthService authService;

    @PostMapping("/login")
    public ResponseEntity<AuthResponse> login(@RequestBody LoginRequest request)
    {

        AuthResponse response = authService.login(request);

        return ResponseEntity.ok(response);
    }


    @PostMapping("/register")
    public ResponseEntity<AuthResponse> register(@RequestBody SignupRequest request) {
        AuthResponse response = authService.register(request);
        return ResponseEntity.ok(response);
    }

}
