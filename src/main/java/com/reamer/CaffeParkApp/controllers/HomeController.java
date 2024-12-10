package com.reamer.CaffeParkApp.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HomeController {

    // This will handle requests to the root URL "/"
    @GetMapping("/")
    public String home() {
        return "Welcome to CaffePark API!";
    }
}
