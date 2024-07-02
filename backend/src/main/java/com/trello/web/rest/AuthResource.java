package com.trello.web.rest;

import javax.validation.Valid;

import org.springframework.http.HttpCookie;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseCookie;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.trello.domain.User;
import com.trello.service.AuthService;
import com.trello.service.JWTService;
import com.trello.service.dto.LoginUserDTO;
import com.trello.service.dto.RegisterUserDTO;
import com.trello.service.responses.LoginResponse;

import lombok.extern.slf4j.Slf4j;

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
    public ResponseEntity<LoginResponse> login(@Valid @RequestBody LoginUserDTO LoginUserDTO) {
        User registeredUser = authService.login(LoginUserDTO);
        String jwtToken = jwtService.generateToken(registeredUser);
        LoginResponse loginResponse = new LoginResponse()
                .setToken(jwtToken)
                .setExpiresAt(jwtService.getExpirationTime(jwtToken));
        HttpCookie jwt = ResponseCookie
                .from("jwt", jwtToken)
                .path("/")
                .httpOnly(true)
                .build();
        return ResponseEntity.ok().header(HttpHeaders.SET_COOKIE, jwt.toString()).body(loginResponse);
    }

    @PostMapping("/register")
    public ResponseEntity<User> register(@Validated @RequestBody RegisterUserDTO registerUserDTO) {
        User user = authService.register(registerUserDTO);
        return ResponseEntity.ok(user);
    }
}
