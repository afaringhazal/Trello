package com.trello.service.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

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
public class SubTaskDTO implements Serializable {

    private static final long serialVersionUID = -7990216963349610621L;

    @JsonProperty("id")
    private Long id;

    @JsonProperty("title")
    @NotNull
    private String title;

    @JsonProperty("isCompleted")
    private Boolean isCompleted;

    @JsonProperty("taskId")
    @NotNull
    private Long taskId;

    @JsonProperty("userId")
    @NotNull
    private Long userId;

    @JsonProperty("createdAt")
    private LocalDateTime createdAt;

    @JsonProperty("updatedAt")
    private LocalDateTime updatedAt;
}
