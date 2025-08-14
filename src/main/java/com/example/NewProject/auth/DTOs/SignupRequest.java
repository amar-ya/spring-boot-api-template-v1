package com.example.NewProject.auth.DTOs;

import com.example.NewProject.users.Role;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class SignupRequest
{
    @NotBlank
    private String username;
    @NotBlank
    @Email
    private String email;

    // Optional: phone number
    private String pn;
    @NotBlank
    private String password;
    // BUYER or SELLER
    private Role role;
}
