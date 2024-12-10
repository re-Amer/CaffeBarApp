package com.reamer.CaffeParkApp.entities;

import jakarta.persistence.*;
import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

@Entity
@Table(name = "users")  // Optional, rename table if needed
@Data
public class User implements UserDetails {
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

    // Implement methods from UserDetails

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        // Map the user's role to a GrantedAuthority
        return List.of(() -> role.name());
    }

    @Override
    public String getPassword() {
        return passwordHash;
    }

    @Override
    public String getUsername() {
        return username;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true; // Adjust logic if needed
    }

    @Override
    public boolean isAccountNonLocked() {
        return true; // Adjust logic if needed
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true; // Adjust logic if needed
    }

    @Override
    public boolean isEnabled() {
        return isActive; // Reflects the `isActive` field
    }


}