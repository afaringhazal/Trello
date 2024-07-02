package com.trello.service.impl;

import com.trello.domain.User;
import com.trello.repository.UserRepository;
import com.trello.service.AuthService;
import com.trello.service.dto.RegisterUserDTO;
import com.trello.service.mapper.AuthMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
@Transactional(rollbackFor = Exception.class)
public class AuthServiceImpl implements AuthService {
    private final UserRepository userRepository;
    private final AuthMapper authMapper;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;

    public AuthServiceImpl(
            UserRepository userRepository,
            AuthMapper authMapper,
            PasswordEncoder passwordEncoder,
            AuthenticationManager authenticationManager
    ) {
        this.userRepository = userRepository;
        this.authMapper = authMapper;
        this.passwordEncoder = passwordEncoder;
        this.authenticationManager = authenticationManager;
    }

    @Override
    public User register(RegisterUserDTO registerUserDTO) {
        User user = new User()
                .setEmail(registerUserDTO.getEmail())
                .setUsername(registerUserDTO.getUsername())
                .setPasswordHash(passwordEncoder.encode(registerUserDTO.getPassword()));
        return userRepository.save(user);
    }

    @Override
    public User login(RegisterUserDTO registerUserDTO) {
        if (registerUserDTO.getEmail() == null && registerUserDTO.getUsername() == null)
            throw new IllegalArgumentException("either username or email must be present");

        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        registerUserDTO.getUsername(),
                        registerUserDTO.getPassword())
        );
        return userRepository.findByUsername(registerUserDTO.getUsername())
                .orElseThrow();
    }
}
