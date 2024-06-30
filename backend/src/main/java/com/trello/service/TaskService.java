package com.trello.service;


import com.trello.service.dto.TaskDTO;
import com.trello.service.dto.WorkspaceDTO;

public interface TaskService {
    TaskDTO getTasksByWorkspaceId(Long workspaceId);

    TaskDTO createTask(TaskDTO taskDTO);

    TaskDTO getByTaskId(Long taskId);

    void Updatetask(TaskDTO taskDTO, Long taskId);

    void deleteById(Long taskId);

}

