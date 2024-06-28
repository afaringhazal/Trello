package com.trello.service.dto;

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

    private static final long serialVersionUID = -7990216963349610621L;

    @JsonProperty("id")
    private Long id;

    @JsonProperty("title")
    @NotNull
    private String title;

    @JsonProperty("status")
    private TaskStatusType status;

    @JsonProperty("description")
    private String description;

    @JsonProperty("createdAt")
    private LocalDateTime estimatedTime;
    @JsonProperty("createdAt")
    private LocalDateTime actualTime;
    @JsonProperty("createdAt")
    private LocalDateTime dueDate;

    @JsonProperty("WorkspaceId")
    @NotNull
    private Long workspaceId;
    @JsonProperty("priority")
    private PriorityType priority;

    @JsonProperty("createdAt")
    private LocalDateTime createdAt;

    @JsonProperty("updatedAt")
    private LocalDateTime updatedAt;

    @JsonProperty("imageURL")
    private String imageURL;

    @NotNull
    @Valid
    @JsonProperty("tasks")
    private Set<SubTaskDTO> subTasks;
}
