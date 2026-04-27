package com.bridgelabz.fundoonote.service.impl;

import com.bridgelabz.fundoonote.dto.LoginDTO;
import com.bridgelabz.fundoonote.dto.UserRegistrationDTO;
import com.bridgelabz.fundoonote.entity.User;
import com.bridgelabz.fundoonote.repository.UserRepository;
import com.bridgelabz.fundoonote.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    // ✅ REGISTER
    @Override
    public String register(UserRegistrationDTO dto) {

        // Create User object
        User user = new User();

        // Set values from DTO
        user.setFirstName(dto.getFirstName());
        user.setLastName(dto.getLastName());
        user.setEmail(dto.getEmail());
        user.setPassword(dto.getPassword());

        // Save to DB
        userRepository.save(user);

        return "User Registered Successfully";
    }

    // ✅ LOGIN
    @Override
    public String login(LoginDTO dto) {

        // Find user by email
        User user = userRepository.findByEmail(dto.getEmail());

        if (user == null) {
            return "User not found";
        }

        // Check password
        if (!user.getPassword().equals(dto.getPassword())) {
            return "Invalid password";
        }

        return "Login Successful";
    }
}