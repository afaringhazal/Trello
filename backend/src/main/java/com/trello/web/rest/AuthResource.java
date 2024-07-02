package com.trello.web.rest;

import com.trello.domain.User;
import com.trello.service.AuthService;
import com.trello.service.JWTService;
import com.trello.service.dto.RegisterUserDTO;
import com.trello.service.responses.LoginResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@Slf4j
@RestController
@Validated
@RequestMapping("/api/auth")
public class AuthResource {
    private final JWTService jwtService;
    private final AuthService authService;

    public AuthResource(JWTService jwtService, AuthService authService) {
        this.jwtService = jwtService;
        this.authService = authService;
    }

    @PostMapping("/login")
    public ResponseEntity<User> login(@Valid @RequestBody RegisterUserDTO registerUserDTO) {
        User registeredUser = authService.login(registerUserDTO);
        return ResponseEntity.ok(registeredUser);
    }

    @PostMapping("/register")
    public ResponseEntity<LoginResponse> register(@Validated @RequestBody RegisterUserDTO registerUserDTO) {
        User user = authService.register(registerUserDTO);
        String jwtToken = jwtService.generateToken(user);
        LoginResponse loginResponse = new LoginResponse()
                .setToken(jwtToken)
                .setExpiresAt(jwtService.getExpirationTime(jwtToken));
        return ResponseEntity.ok(loginResponse);
    }
}