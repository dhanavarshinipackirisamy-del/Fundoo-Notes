package com.bridgelabz.fundoonote.service;

import com.bridgelabz.fundoonote.dto.LoginDTO;
import com.bridgelabz.fundoonote.dto.UserRegistrationDTO;

public interface UserService {

    String register(UserRegistrationDTO dto);

    String login(LoginDTO dto);
}