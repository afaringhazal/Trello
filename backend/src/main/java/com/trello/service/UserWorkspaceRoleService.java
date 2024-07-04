package com.trello.service;

import com.trello.domain.UserWorkspaceRole;
import com.trello.service.dto.UserDTO;
import com.trello.service.dto.UserWorkspaceRoleDTO;

import java.util.List;

public interface UserWorkspaceRoleService {
    List<UserDTO> getUsersByWorkspace(Long workspaceId);

    UserWorkspaceRoleDTO create(UserWorkspaceRoleDTO userWorkspaceRoleDTO);

    void Update(UserWorkspaceRoleDTO userWorkspaceRoleDTO);

    void delete(Long workspaceId, Long userId);

    UserWorkspaceRole get(Long workspaceId, Long userId);
}
