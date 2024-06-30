package com.trello.service;


import com.trello.service.dto.WorkspaceDTO;

import java.util.List;

public interface WorkspaceService {


    List<WorkspaceDTO> getAllWorkspace();

    WorkspaceDTO createWorkspace(WorkspaceDTO workspaceDTO);

    WorkspaceDTO getByWorkspaceId(Long workspaceId);

    void UpdateWorkspace(WorkspaceDTO workspaceDTO, Long workspaceId);

    void deleteById(Long workspaceId);
}
