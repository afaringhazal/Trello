package com.trello.service.mapper;

import com.trello.domain.Workspace;
import com.trello.service.dto.WorkspaceDTO;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface WorkspaceMapper extends EntityMapper<Workspace, WorkspaceDTO> {

    @Override
    Workspace toEntity(WorkspaceDTO workspaceDTO);

    @Override
    WorkspaceDTO toDTO(Workspace workspace);

    @Override
    List<Workspace> toEntity(List<WorkspaceDTO> dtoList);

    @Override
    List<WorkspaceDTO> toDTO(List<Workspace> entityList);

}
