package com.trello.service.responses;

import java.util.Date;

public class LoginResponse {
    private String token;

    private Date expiresAt;

    public String getToken() {
        return token;
    }

    public LoginResponse setToken(String token) {
        this.token = token;
        return this;
    }

    public Date getExpiresAt() {
        return expiresAt;
    }

    public LoginResponse setExpiresAt(Date expiresAt) {
        this.expiresAt = expiresAt;
        return this;
    }

    @Override
    public String toString() {
        return "LoginResponse{" +
                "token='" + token + '\'' +
                ", expiresAt=" + expiresAt +
                '}';
    }
}
