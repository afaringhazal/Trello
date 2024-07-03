package com.trello.service.impl;

import com.trello.domain.Workspace;
import com.trello.repository.WorkspaceRepository;
import com.trello.service.WorkspaceService;
import com.trello.service.dto.WorkspaceDTO;
import com.trello.service.mapper.WorkspaceMapper;
import lombok.extern.slf4j.Slf4j;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional(rollbackFor = Exception.class)
@Slf4j
public class WorkspaceServiceImpl implements WorkspaceService {

    private final WorkspaceRepository workspaceRepository;
    private final WorkspaceMapper workspaceMapper;

    public WorkspaceServiceImpl(WorkspaceRepository workspaceRepository, WorkspaceMapper workspaceMapper) {
        this.workspaceRepository = workspaceRepository;
        this.workspaceMapper = workspaceMapper;
    }

    @Override
    public List<WorkspaceDTO> getAllWorkspace() {
        List<Workspace> workspaces = workspaceRepository.findAll();
        return workspaceMapper.toDTO(workspaces);
    }

    @Override
    public WorkspaceDTO createWorkspace(WorkspaceDTO workspaceDTO) {
        if(workspaceDTO.getName().isBlank())
            throw new IllegalArgumentException("Workspace name cannot be blank");
        if(workspaceDTO.getName().length() > 255)
            throw new IllegalArgumentException("Workspace name cannot be longer than 255 characters");

        Workspace savedWorkspace = workspaceRepository.save(
                workspaceMapper.toEntity(workspaceDTO));
        return workspaceMapper.toDTO(savedWorkspace);
    }

    @Override
    public WorkspaceDTO getByWorkspaceId(Long workspaceId) {
        Optional<Workspace> workspace = workspaceRepository.findById(workspaceId);
        if(workspace.isEmpty())
            throw new IllegalArgumentException("Workspace is not exist");

        return workspaceMapper.toDTO(workspace.get());
    }

    @Override
    public void UpdateWorkspace(WorkspaceDTO workspaceDTO, Long workspaceId) {
        if(workspaceDTO.getName().isBlank())
            throw new IllegalArgumentException("New workspace name cannot be blank");
        if(workspaceDTO.getName().length() > 255)
            throw new IllegalArgumentException("New workspace name cannot be longer than 255 characters");

        if(workspaceDTO.getId() != null && !workspaceDTO.getId().equals(workspaceId))
            throw new IllegalArgumentException("workspace id is not valid!");

        Optional<Workspace> workspace = workspaceRepository.findById(workspaceId);
        if(workspace.isEmpty())
            throw new IllegalArgumentException("Workspace is not exist");

        workspaceDTO.setId(workspaceId);
        workspaceRepository.save(workspaceMapper.toEntity(workspaceDTO));
    }

    @Override
    public void deleteById(Long workspaceId) {
        if(workspaceId == null || !workspaceRepository.existsById(workspaceId))
            throw new IllegalArgumentException("Workspace is not exist");
        workspaceRepository.deleteById(workspaceId);
    }
}
