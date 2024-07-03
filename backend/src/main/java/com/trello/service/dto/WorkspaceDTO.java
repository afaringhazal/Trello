package com.trello.service.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Set;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class WorkspaceDTO implements Serializable {

    private static final long serialVersionUID = -7990216963349610621L;

    @JsonProperty("id")
    private Long id;

    @JsonProperty("name")
    @NotNull
    private String name;

    @JsonProperty("description")
    private String description;

    @JsonProperty("createdAt")
    private LocalDateTime createdAt;

    @JsonProperty("updatedAt")
    private LocalDateTime updatedAt;

    @NotNull
    @Valid
    @JsonProperty("tasks")
    private Set<TaskDTO> tasks;
}
