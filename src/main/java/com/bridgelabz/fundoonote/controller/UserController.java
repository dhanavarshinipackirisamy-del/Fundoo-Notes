package com.bridgelabz.fundoonote.controller;

import com.bridgelabz.fundoonote.dto.LoginDTO;
import com.bridgelabz.fundoonote.dto.UserRegistrationDTO;
import com.bridgelabz.fundoonote.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/user")
@RequiredArgsConstructor

    public class UserController {


        private final UserService userService;

        @PostMapping("/register")
        public String register(@RequestBody UserRegistrationDTO dto) {
            return userService.register(dto);
        }

        @PostMapping("/login")
        public Map<String, String> login(@RequestBody LoginDTO dto) {
            String token = userService.login(dto);
            return Map.of("token", token);
        }
    }

