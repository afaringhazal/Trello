package com.trello.service.mapper;

import com.trello.domain.Task;
import com.trello.service.dto.TaskDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface TaskMapper extends EntityMapper<Task, TaskDTO> {
    @Override
    @Mapping(source = "workspaceId", target = "workspace.id")
    @Mapping(source = "userId", target = "user.id")
    Task toEntity(TaskDTO TaskDTO);

    @Override
    @Mapping(source = "workspace.id", target = "workspaceId")
    @Mapping(source = "user.id", target = "userId")
    TaskDTO toDTO(Task Task);

    @Override
    @Mapping(source = "workspaceId", target = "workspace.id")
    @Mapping(source = "userId", target = "user.id")
    List<Task> toEntity(List<TaskDTO> dtoList);

    @Override
    @Mapping(source = "workspace.id", target = "workspaceId")
    @Mapping(source = "user.id", target = "userId")
    List<TaskDTO> toDTO(List<Task> entityList);

}
