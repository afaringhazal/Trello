package com.trello.service.impl;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.trello.domain.User;
import com.trello.repository.UserRepository;
import com.trello.service.AuthService;
import com.trello.service.dto.LoginUserDTO;
import com.trello.service.dto.RegisterUserDTO;

import lombok.extern.slf4j.Slf4j;

import java.time.LocalDateTime;

@Slf4j
@Service
@Transactional(rollbackFor = Exception.class)
public class AuthServiceImpl implements AuthService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;

    public AuthServiceImpl(
            UserRepository userRepository,
            PasswordEncoder passwordEncoder,
            AuthenticationManager authenticationManager) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.authenticationManager = authenticationManager;
    }

    @Override
    public User register(RegisterUserDTO registerUserDTO) {
        User user = new User()
                .setEmail(registerUserDTO.getEmail())
                .setUsername(registerUserDTO.getUsername())
                .setPasswordHash(passwordEncoder.encode(registerUserDTO.getPassword()));
        user.setCreatedAt(LocalDateTime.now());
        return userRepository.save(user);
    }

    @Override
    public User login(LoginUserDTO loginUserDTO) {
        if (loginUserDTO.getEmail() == null && loginUserDTO.getUsername() == null)
            throw new IllegalArgumentException("either username or email must be present");

        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        loginUserDTO.getUsername(),
                        loginUserDTO.getPassword()));
        return userRepository.findByUsername(loginUserDTO.getUsername())
                .orElseThrow();
    }
}
