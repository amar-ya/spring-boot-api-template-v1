package com.example.NewProject.users;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;

@Entity
@Table(name = "users")
@Data
public class UserEntity
{
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "user_seq")
    @SequenceGenerator(name = "user_seq", sequenceName = "user_seq", allocationSize = 1)
    private Integer id;
    private String email;
    private String password;
    private String username;
    private String phoneNumber;
    private LocalDateTime createdAt;
    private Boolean isVerified;
    @Enumerated(EnumType.STRING)
    private Role role;
}
