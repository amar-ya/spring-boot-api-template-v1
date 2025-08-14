package com.example.NewProject.auth.service;

import com.example.NewProject.auth.DTOs.AuthResponse;
import com.example.NewProject.auth.DTOs.LoginRequest;
import com.example.NewProject.auth.DTOs.SignupRequest;
import com.example.NewProject.security.JwtUtil;
import com.example.NewProject.users.UserEntity;
import com.example.NewProject.users.UserRepository;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@Data

@AllArgsConstructor
public class AuthService
{
    private final UserRepository userRepository;
    private final JwtUtil jwtUtil;
    private final PasswordEncoder passwordEncoder;


    //Login
    public AuthResponse login(LoginRequest request)
    {

        // Step 1: Find user by email
        UserEntity user = userRepository.findByEmail(request.getEmail())
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));

        // Step 2: Validate password
        if(!passwordEncoder.matches(request.getPassword(), user.getPassword()))
        {
            throw new BadCredentialsException("Invalid password");
        }

        // Step 3: Generate token
        String token = jwtUtil.generateToken(user.getEmail(), user.getRole().name());

        //step 4 : return structured response
        return new AuthResponse(token, user.getUsername(), user.getRole());
    }

    // Signup

    public AuthResponse register(SignupRequest request) {
        // Step 1: Check if email already exists
        if (userRepository.findByEmail(request.getEmail()).isPresent()) {
            throw new IllegalArgumentException("Email is already registered.");
        }

        // Step 2: Create user entity
        UserEntity user = new UserEntity();
        user.setUsername(request.getUsername());
        user.setEmail(request.getEmail());
        user.setPhoneNumber(request.getPn());
        user.setPassword(passwordEncoder.encode(request.getPassword()));
        user.setRole(request.getRole());
        user.setCreatedAt(LocalDateTime.now());
        user.setIsVerified(true); // or false if you want to handle verification later

        //test to see why do i get 403 forbidden response


        // Step 3: Save user
        userRepository.save(user);

        // Step 4: Generate JWT
        String token = jwtUtil.generateToken(user.getEmail(), user.getRole().name());


        // Step 5: Return response
        return new AuthResponse(token, user.getUsername(), user.getRole());



    }

}

