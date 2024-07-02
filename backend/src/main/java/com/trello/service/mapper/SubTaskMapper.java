package com.trello.service.mapper;

import com.trello.domain.SubTask;
import com.trello.domain.Task;
import com.trello.service.dto.SubTaskDTO;
import com.trello.service.dto.TaskDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface SubTaskMapper extends EntityMapper<SubTask, SubTaskDTO> {
    @Override
    @Mapping(source = "taskId", target = "task.id")
    @Mapping(source = "userId", target = "user.id")
    SubTask toEntity(SubTaskDTO subTaskDTO);

    @Override
    @Mapping(source = "task.id", target = "taskId")
    @Mapping(source = "user.id", target = "userId")
    SubTaskDTO toDTO(SubTask subTask);

    @Override
    @Mapping(source = "taskId", target = "task.id")
    @Mapping(source = "userId", target = "user.id")
    List<SubTask> toEntity(List<SubTaskDTO> dtoList);

    @Override
    @Mapping(source = "task.id", target = "taskId")
    @Mapping(source = "user.id", target = "userId")
    List<SubTaskDTO> toDTO(List<SubTask> entityList);

}
