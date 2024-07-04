package com.trello.service.impl;

import com.trello.domain.Task;
import com.trello.domain.Workspace;
import com.trello.repository.TaskRepository;
import com.trello.repository.WorkspaceRepository;
import com.trello.service.WorkspaceService;
import com.trello.service.dto.WorkspaceDTO;
import com.trello.service.mapper.WorkspaceMapper;
import lombok.extern.slf4j.Slf4j;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
@Transactional(rollbackFor = Exception.class)
@Slf4j
public class WorkspaceServiceImpl implements WorkspaceService {

    private final WorkspaceRepository workspaceRepository;
    private final WorkspaceMapper workspaceMapper;
    private final TaskRepository taskRepository;
    public WorkspaceServiceImpl(WorkspaceRepository workspaceRepository, WorkspaceMapper workspaceMapper, TaskRepository taskRepository) {
        this.workspaceRepository = workspaceRepository;
        this.workspaceMapper = workspaceMapper;
        this.taskRepository = taskRepository;
    }

    @Override
    public List<WorkspaceDTO> getAllWorkspace() {
        List<Workspace> workspaces = workspaceRepository.findAll();
        return workspaceMapper.toDTO(workspaces);
    }

    @Override
    public WorkspaceDTO createWorkspace(WorkspaceDTO workspaceDTO) {
        if(workspaceDTO.getId() != null) {
            throw new IllegalArgumentException("workspace id must be null");
        }
        if(workspaceDTO.getName().isBlank())
            throw new IllegalArgumentException("Workspace name cannot be blank");
        if(workspaceDTO.getName().length() > 255)
            throw new IllegalArgumentException("Workspace name cannot be longer than 255 characters");
        workspaceDTO.setCreatedAt(LocalDateTime.now());
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
        workspaceDTO.setUpdatedAt(LocalDateTime.now());
        workspaceDTO.setCreatedAt(workspace.get().getCreatedAt());
        workspaceRepository.save(workspaceMapper.toEntity(workspaceDTO));
    }

    @Override
    public void deleteById(Long workspaceId) {
        if(workspaceId == null || !workspaceRepository.existsById(workspaceId))
            throw new IllegalArgumentException("Workspace is not exist");
        Optional<Task> task = taskRepository.findByWorkspaceId(workspaceId);
        if(task.isPresent()){
            throw new IllegalArgumentException("task found with id " + workspaceId);
        }
        workspaceRepository.deleteById(workspaceId);
    }
}
