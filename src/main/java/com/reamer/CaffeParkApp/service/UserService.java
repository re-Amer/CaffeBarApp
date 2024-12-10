package com.reamer.CaffeParkApp.service;

import com.reamer.CaffeParkApp.dto.LoginRequest;
import com.reamer.CaffeParkApp.dto.UserDto;
import com.reamer.CaffeParkApp.entities.User;
import com.reamer.CaffeParkApp.repository.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UserService { // Removed "implements UserDetailsService"

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    // Removed loadUserByUsername() method

    public UserDto register(LoginRequest request) {
        User user = new User();
        user.setUsername(request.getUsername());
        user.setPasswordHash(passwordEncoder.encode(request.getPassword()));
        user.setRole(User.Role.ADMIN); // Default role
        user.setActive(true);

        User savedUser = userRepository.save(user);
        return convertToDto(savedUser);
    }

    public User createUser(User user) {
        user.setPasswordHash(passwordEncoder.encode(user.getPassword()));
        user.setActive(true); // Default active state
        user.setRole(User.Role.ADMIN); // Default to ADMIN or any existing role
        return userRepository.save(user);
    }

    public List<UserDto> getAllUsers() {
        return userRepository.findAll().stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }

    public Optional<UserDto> getUserById(int id) {
        return userRepository.findById(id)
                .map(this::convertToDto);
    }

    public void deleteUser(int id) {
        userRepository.deleteById(id);
    }

    public UserDto updateUser(int id, User userDetails) {
        return userRepository.findById(id)
                .map(user -> {
                    user.setUsername(userDetails.getUsername());
                    if (userDetails.getPasswordHash() != null) {
                        user.setPasswordHash(passwordEncoder.encode(userDetails.getPasswordHash()));
                    }
                    user.setRole(userDetails.getRole());
                    user.setActive(userDetails.isActive());
                    return convertToDto(userRepository.save(user));
                })
                .orElseThrow(() -> new RuntimeException("User not found"));
    }

    private UserDto convertToDto(User user) {
        UserDto dto = new UserDto();
        dto.setId(user.getId());
        dto.setUsername(user.getUsername());
        dto.setRole(user.getRole());
        dto.setActive(user.isActive());
        return dto;
    }
}