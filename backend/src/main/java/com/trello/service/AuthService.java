package com.trello.service;

import com.trello.domain.User;
import com.trello.service.dto.LoginUserDTO;
import com.trello.service.dto.RegisterUserDTO;

public interface AuthService {
    User register(RegisterUserDTO registerUserDTO);
    User login(LoginUserDTO registerUserDTO);
}
