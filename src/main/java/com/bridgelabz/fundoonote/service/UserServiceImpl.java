package com.bridgelabz.fundoonote.service;

import com.bridgelabz.fundoonote.dto.LoginDTO;
import com.bridgelabz.fundoonote.dto.UserRegistrationDTO;
import com.bridgelabz.fundoonote.entity.User;
import com.bridgelabz.fundoonote.repository.UserRepository;
import com.bridgelabz.fundoonote.util.JwtUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.*;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtUtil jwtUtil;

    // 🔹 REGISTER
    @Override
    public String register(UserRegistrationDTO dto) {

        if (userRepository.findByEmail(dto.getEmail()).isPresent()) {
            throw new RuntimeException("Email already exists");
        }

        User user = new User();
        user.setEmail(dto.getEmail());
        user.setPassword(passwordEncoder.encode(dto.getPassword()));

        userRepository.save(user);

        return "User registered successfully";
    }

    // 🔹 LOGIN
    @Override
    public String login(LoginDTO dto) {

        User user = userRepository.findByEmail(dto.getEmail())
                .orElseThrow(() -> new RuntimeException("User not found"));

        if (!passwordEncoder.matches(dto.getPassword(), user.getPassword())) {
            throw new RuntimeException("Invalid password");
        }

        return jwtUtil.generateToken(user.getEmail());
    }

}