package com.trello.service.impl;

import java.util.Optional;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.trello.domain.Task;
import com.trello.domain.Workspace;
import com.trello.repository.TaskRepository;
import com.trello.repository.UserRepository;
import com.trello.repository.WorkspaceRepository;
import com.trello.service.TaskService;
import com.trello.service.dto.TaskDTO;
import com.trello.service.dto.UserDTO;
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

    public TaskServiceImpl(TaskRepository taskRepository, TaskMapper taskMapper, WorkspaceRepository workspaceRepository, UserRepository userRepository) {
        this.taskRepository = taskRepository;
        this.taskMapper = taskMapper;
        this.workspaceRepository = workspaceRepository;
        this.userRepository = userRepository;
    }

    @Override
    public TaskDTO getTasksByWorkspaceId(Long workspaceId) {
        Optional<Task> task = taskRepository.findByWorkspace(workspaceId);
        if (task.isPresent()) {
            return taskMapper.toDTO(task.get());
        }
        return null;
    }

    @Override
    public TaskDTO createTask(TaskDTO taskDTO) {
        if (taskDTO.getTitle() == null || taskDTO.getTitle().isEmpty()) {
            throw new IllegalArgumentException("Task title cannot be empty");
        }
        if (taskDTO.getWorkspaceId() == null)
            throw new IllegalArgumentException("Workspace id cannot be empty");

        Optional<Workspace> workspace = workspaceRepository.findById(taskDTO.getWorkspaceId());
        if (!workspace.isPresent()) {
            throw new IllegalArgumentException("Workspace not found");
        }
        if(taskDTO.getUserId() != null){ // optional
            userRepository.findById(taskDTO.getUserId()).orElseThrow(() -> new IllegalArgumentException("User not found"));
        }

        Task task = taskMapper.toEntity(taskDTO);
        taskRepository.save(task);
        return null;
    }

    @Override
    public TaskDTO getByTaskId(Long taskId) {
        if (taskId == null)
            throw new IllegalArgumentException("Task id cannot be null");
        return taskMapper.toDTO(taskRepository.findById(taskId).get());
    }

    @Override
    public void Updatetask(TaskDTO taskDTO, Long taskId) {
        if (taskDTO.getTitle() == null || taskDTO.getTitle().isEmpty()) {
            throw new IllegalArgumentException("New task title cannot be empty");
        }
        if (taskDTO.getWorkspaceId() == null)
            throw new IllegalArgumentException("Workspace id cannot be empty");

        Optional<Workspace> workspace = workspaceRepository.findById(taskDTO.getWorkspaceId());
        if (!workspace.isPresent()) {
            throw new IllegalArgumentException("Workspace not found");
        }
        // TODO : assignee_id must be valid

        Optional<Task> originalTask = taskRepository.findById(taskId);
        if (!originalTask.isPresent()) {
            throw new IllegalArgumentException("Task not found");
        }
        taskDTO.setId(taskId);
        taskRepository.save(taskMapper.toEntity(taskDTO));
    }

    @Override
    public void deleteById(Long taskId) {
        if (taskId == null)
            throw new IllegalArgumentException("Task id cannot be null");
        taskRepository.deleteById(taskId);
    }
}
