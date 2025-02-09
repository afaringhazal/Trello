package com.trello.service.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.trello.domain.enumeration.RoleType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.time.LocalDateTime;


@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserWorkspaceRoleDTO implements Serializable {

    private static final long serialVersionUID = -7990216963349610621L;

    @JsonProperty("role")
    private RoleType role;

    @JsonProperty("WorkspaceId")
    @NotNull
    private Long workspaceId;

    @JsonProperty("UserId")
    @NotNull
    private Long userId;

    @JsonProperty("createdAt")
    private LocalDateTime createdAt;

    @JsonProperty("updatedAt")
    private LocalDateTime updatedAt;

}
