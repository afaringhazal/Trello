package com.trello.service;

import java.util.List;

import com.trello.service.dto.WorkspaceDTO;

public interface WorkspaceService {

    List<WorkspaceDTO> getAllWorkspace();

    WorkspaceDTO createWorkspace(WorkspaceDTO workspaceDTO);

    WorkspaceDTO getByWorkspaceId(Long workspaceId);

    void UpdateWorkspace(WorkspaceDTO workspaceDTO, Long workspaceId);

    void deleteById(Long workspaceId);
}
