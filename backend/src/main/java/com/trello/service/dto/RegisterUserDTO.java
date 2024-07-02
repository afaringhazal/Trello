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
    @NotNull(message = "missing username")
    @Pattern(regexp = "[\\w_]+", message = "invalid username")
    private String username;

    @JsonProperty("email")
    @NotNull(message = "missing email")
    @Pattern(regexp = "[a-zA-Z0-9._+-]+@([a-zA-Z0-9-]+\\.)+[A-Za-z]{2,}", message = "invalid email")
    private String email;

    @JsonProperty("password")
    @NotNull(message = "missing password")
    @Pattern(regexp = "\\w{8,}", message = "invalid password, valid password format is `\\w{8,}`")
    private String password;
}
