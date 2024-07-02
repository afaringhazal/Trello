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
public class LoginUserDTO implements Serializable {
    @JsonProperty("username")
    @Pattern(regexp = "[\\w_]+")
    private String username;

    @JsonProperty("email")
    @Pattern(regexp = "[a-zA-Z0-9._+-]+@([a-zA-Z0-9-]+\\.)+[A-Za-z]{2,}")
    private String email;

    @JsonProperty("password")
    @NotNull
    @Pattern(regexp = "\\w{8,}")
    private String password;
}
