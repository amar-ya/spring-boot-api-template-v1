package com.example.NewProject.auth.DTOs;

import com.example.NewProject.users.Role;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AuthResponse
{
    private String token;
    private String username;
    private Role role;
}