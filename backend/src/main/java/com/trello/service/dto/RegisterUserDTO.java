package com.trello.service.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.io.Serializable;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RegisterUserDTO implements Serializable {
    @JsonProperty("username")
    @NotNull(message = "provide a username")
    @Pattern(regexp = "[\\w_]+")
    private String username;

    @JsonProperty("email")
    @NotNull(message = "provide an email")
    @Pattern(regexp = "[a-zA-Z0-9._+-]+@([a-zA-Z0-9-]+\\.)+[A-Za-z]{2,}")
    private String email;

    @JsonProperty("password")
    @NotNull(message = "provide a password")
    @Pattern(regexp = "\\w{8,}")
    private String password;
}
