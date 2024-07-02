package com.trello.service;

import com.trello.service.dto.SubTaskDTO;

import java.util.List;

public interface SubTaskService {
    List<SubTaskDTO> getSubTasksByTaskId(Long taskId);

    SubTaskDTO createSubTask(SubTaskDTO subTaskDTO);

    SubTaskDTO getBySubTaskId(Long subTaskId);

    void UpdateSubTask(SubTaskDTO subTaskDTO, Long subTaskId);

    void deleteById(Long subTaskId);
}
