package com.trello.service.impl;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import com.trello.service.SubTaskService;
import com.trello.service.UserWorkspaceRoleService;
import com.trello.service.dto.SubTaskDTO;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.trello.domain.Task;
import com.trello.domain.Workspace;
import com.trello.repository.TaskRepository;
import com.trello.repository.UserRepository;
import com.trello.repository.WorkspaceRepository;
import com.trello.service.TaskService;
import com.trello.service.dto.TaskDTO;
import com.trello.service.mapper.TaskMapper;

import lombok.extern.slf4j.Slf4j;

@Service
@Transactional(rollbackFor = Exception.class)
@Slf4j
public class TaskServiceImpl implements TaskService {

    private final TaskRepository taskRepository;
    private final TaskMapper taskMapper;
    private final WorkspaceRepository workspaceRepository;
    private final UserRepository userRepository;
    private final UserWorkspaceRoleService roleService;
    private final SubTaskService subTaskService;

    public TaskServiceImpl(TaskRepository taskRepository, TaskMapper taskMapper, WorkspaceRepository workspaceRepository, UserRepository userRepository, UserWorkspaceRoleService roleService, SubTaskService subTaskService) {
        this.taskRepository = taskRepository;
        this.taskMapper = taskMapper;
        this.workspaceRepository = workspaceRepository;
        this.userRepository = userRepository;
        this.roleService = roleService;
        this.subTaskService = subTaskService;
    }

    @Override
    public TaskDTO getTasksByWorkspaceId(Long workspaceId) {
        Optional<Task> task = taskRepository.findByWorkspaceId(workspaceId);
        if (task.isPresent()) {
            return taskMapper.toDTO(task.get());
        }
        return null;
    }

    @Override
    public TaskDTO createTask(TaskDTO taskDTO) {
        if(taskDTO.getId()!=null){
            throw new IllegalArgumentException("Task ID must not be null");
        }
        if (taskDTO.getTitle() == null || taskDTO.getTitle().isEmpty()) {
            throw new IllegalArgumentException("Task title cannot be empty");
        }
        if (taskDTO.getWorkspaceId() == null)
            throw new IllegalArgumentException("Workspace id cannot be empty");

        Optional<Workspace> workspace = workspaceRepository.findById(taskDTO.getWorkspaceId());
        if (!workspace.isPresent()) {
            throw new IllegalArgumentException("Workspace not found");
        }
        if(taskDTO.getUserId() == null){
            throw new IllegalArgumentException("user id cannot be empty");
        }
        userRepository.findById(taskDTO.getUserId()).orElseThrow(() -> new IllegalArgumentException("User not found"));

        roleService.get(taskDTO.getWorkspaceId(), taskDTO.getUserId()); // It is visible only to those who have access

        taskDTO.setCreatedAt(LocalDateTime.now());
        Task task = taskMapper.toEntity(taskDTO);
        return taskMapper.toDTO(taskRepository.save(task));
    }

    @Override
    public TaskDTO getByTaskId(Long taskId) {
        if (taskId == null)
            throw new IllegalArgumentException("Task id cannot be null");
        return taskMapper.toDTO(taskRepository.findById(taskId).get());
    }

    @Override
    public void updateTask(TaskDTO taskDTO, Long taskId) {
        if(taskDTO.getId() != null && !taskDTO.getId().equals(taskId)){
            throw new IllegalArgumentException("Task ID is not valid");
        }
        if (taskDTO.getTitle() == null || taskDTO.getTitle().isEmpty()) {
            throw new IllegalArgumentException("New task title cannot be empty");
        }
        if (taskDTO.getWorkspaceId() == null)
            throw new IllegalArgumentException("Workspace id cannot be empty");

        Optional<Workspace> workspace = workspaceRepository.findById(taskDTO.getWorkspaceId());
        if (!workspace.isPresent()) {
            throw new IllegalArgumentException("Workspace not found");
        }
        if(taskDTO.getUserId() != null){
            throw new IllegalArgumentException("user id cannot be empty");
        }
        userRepository.findById(taskDTO.getUserId()).orElseThrow(() -> new IllegalArgumentException("User not found"));

        roleService.get(taskDTO.getWorkspaceId(), taskDTO.getUserId()); // It is visible only to those who have access

        Optional<Task> originalTask = taskRepository.findById(taskId);
        if (!originalTask.isPresent()) {
            throw new IllegalArgumentException("Task not found");
        }
        taskDTO.setId(taskId);
        taskDTO.setCreatedAt(originalTask.get().getCreatedAt());
        taskDTO.setUpdatedAt(LocalDateTime.now());
        taskRepository.save(taskMapper.toEntity(taskDTO));
    }

    @Override
    public void deleteById(Long taskId) {
        if (taskId == null)
            throw new IllegalArgumentException("Task id cannot be null");
        List<SubTaskDTO> subTasksByTaskId = subTaskService.getSubTasksByTaskId(taskId);
        if(subTasksByTaskId != null && !subTasksByTaskId.isEmpty()){
            throw new IllegalArgumentException("subtasks found by task id"+taskId);
        }
        taskRepository.deleteById(taskId);
    }
}
