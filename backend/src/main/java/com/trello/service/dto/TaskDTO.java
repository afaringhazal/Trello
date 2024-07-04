package com.trello.service.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.trello.domain.enumeration.PriorityType;
import com.trello.domain.enumeration.TaskStatusType;
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
public class TaskDTO implements Serializable {

    @JsonProperty("id")
    private Long id;

    @JsonProperty("title")
    @NotNull
    private String title;

    @JsonProperty("status")
    private TaskStatusType status;

    @JsonProperty("description")
    private String description;

    @JsonProperty("estimatedTime")
    private LocalDateTime estimatedTime;

    @JsonProperty("actualTime")
    private LocalDateTime actualTime;

    @JsonProperty("dueDate")
    private LocalDateTime dueDate;

    @JsonProperty("workspaceId")
    @NotNull
    private Long workspaceId;

    @JsonProperty("userId")
    @NotNull
    private Long userId;

    @JsonProperty("priority")
    private PriorityType priority;

    @JsonProperty("createdAt")
    private LocalDateTime createdAt;

    @JsonProperty("updatedAt")
    private LocalDateTime updatedAt;

    @JsonProperty("imageURL")
    private String imageURL;

    @JsonProperty("subTasks")
    @JsonIgnore
    private Set<SubTaskDTO> subTasks;
}
