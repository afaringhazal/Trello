package com.trello.service.mapper;


import com.trello.domain.UserWorkspaceRole;
import com.trello.service.dto.UserWorkspaceRoleDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface UserWorkspaceRoleMapper extends EntityMapper<UserWorkspaceRole, UserWorkspaceRoleDTO> {
    @Override
    UserWorkspaceRole toEntity(UserWorkspaceRoleDTO userWorkspaceRoleDTO);

    @Override
    UserWorkspaceRoleDTO toDTO(UserWorkspaceRole userWorkspaceRole);

    @Override
    List<UserWorkspaceRole> toEntity(List<UserWorkspaceRoleDTO> dtoList);

    @Override
    List<UserWorkspaceRoleDTO> toDTO(List<UserWorkspaceRole> entityList);

}
