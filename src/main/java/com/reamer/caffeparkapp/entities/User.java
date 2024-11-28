package com.reamer.caffeparkapp.entities;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "users")  // Optional, rename table if needed
@Data
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(nullable = false, unique = true, length = 50)
    private String username;

    @Column(nullable = false, length = 255)
    private String passwordHash;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 10)
    private Role role;

    @Column(nullable = false)
    private boolean isActive = true;

    public enum Role {
        SUPER_ADMIN,
        ADMIN
    }

    // Default constructor for JPA
    public User() {
    }
}

