package com.bridgelabz.fundoonote.service.impl;

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

    @Override
    public String register(UserRegistrationDTO dto) {

        User user = new User();
        user.setFirstName(dto.getFirstName());
        user.setLastName(dto.getLastName());
        user.setEmail(dto.getEmail());
        user.setPassword(dto.getPassword());

        userRepository.save(user);

        return "User Registered Successfully";
    }
}