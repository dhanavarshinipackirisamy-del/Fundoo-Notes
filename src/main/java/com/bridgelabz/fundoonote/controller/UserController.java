package com.bridgelabz.fundoonote.controller;

import com.bridgelabz.fundoonote.dto.UserRegistrationDTO;
import com.bridgelabz.fundoonote.service.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/register")
    public String registerUser(@Valid @RequestBody UserRegistrationDTO dto) {
        return userService.register(dto);
    }
}