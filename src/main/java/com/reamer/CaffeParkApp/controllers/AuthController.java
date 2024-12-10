package com.reamer.CaffeParkApp.controllers;

import com.reamer.CaffeParkApp.dto.LoginRequest;
import com.reamer.CaffeParkApp.dto.UserDto;
import com.reamer.CaffeParkApp.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
@CrossOrigin(origins = "*")
public class AuthController {

    private final UserService userService;

    public AuthController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/register")
    public ResponseEntity<UserDto> register(@RequestBody LoginRequest request) {
        return ResponseEntity.ok(userService.register(request));
    }

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody LoginRequest request) {
        // Authentication is handled by JwtAuthenticationFilter
        return ResponseEntity.ok("Authentication successful");
    }
}