package com.reamer.caffeparkapp.dto;

import com.reamer.caffeparkapp.entities.User;
import lombok.Data;

@Data
public class UserDto {
    private int id;
    private String username;
    private User.Role role;
    private boolean isActive;

    // Don't include password in DTO for security
}