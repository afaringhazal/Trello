package com.trello.service.impl;

import com.trello.domain.SubTask;
import com.trello.domain.Task;
import com.trello.domain.User;
import com.trello.repository.SubTaskRepository;
import com.trello.repository.TaskRepository;
import com.trello.repository.UserRepository;
import com.trello.service.SubTaskService;
import com.trello.service.UserWorkspaceRoleService;
import com.trello.service.dto.SubTaskDTO;
import com.trello.service.mapper.SubTaskMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;


@Service
@Transactional(rollbackFor = Exception.class)
@Slf4j
public class SubTaskServiceImpl implements SubTaskService {
    private final SubTaskRepository subTaskRepository;
    private final SubTaskMapper subTaskMapper;
    private final TaskRepository taskRepository;
    private final UserRepository userRepository;
    private final UserWorkspaceRoleService roleService;

    public SubTaskServiceImpl(SubTaskRepository subTaskRepository, SubTaskMapper subTaskMapper, TaskRepository taskRepository, UserRepository userRepository, UserWorkspaceRoleService roleService) {
        this.subTaskRepository = subTaskRepository;
        this.subTaskMapper = subTaskMapper;
        this.taskRepository = taskRepository;
        this.userRepository = userRepository;
        this.roleService = roleService;
    }


    @Override
    public List<SubTaskDTO> getSubTasksByTaskId(Long taskId) {
        List<SubTask> subtasks = subTaskRepository.findAllByTaskId(taskId);
        return subTaskMapper.toDTO(subtasks);
    }

    @Override
    public SubTaskDTO createSubTask(SubTaskDTO subTaskDTO) {
        if(subTaskDTO.getId() != null) {
            throw new IllegalArgumentException("Subtask id must be null");
        }
        if(subTaskDTO.getTitle().isBlank()){
            throw new IllegalArgumentException("Title cannot be blank");
        }
        if(subTaskDTO.getTaskId() == null){
            throw new IllegalArgumentException("TaskId cannot be null");
        }
        Task task = taskRepository.findById(subTaskDTO.getTaskId()).orElseThrow(() -> new IllegalArgumentException("Task not found"));
        if(subTaskDTO.getUserId() == null){
            throw new IllegalArgumentException("UserId cannot be null");
        }
        User user = userRepository.findById(subTaskDTO.getUserId()).orElseThrow(() -> new IllegalArgumentException("User not found"));
        roleService.get(task.getWorkspace().getId(), subTaskDTO.getUserId());

        SubTask subTask = subTaskMapper.toEntity(subTaskDTO);

        subTask.setTask(task);
        subTask.setUser(user);
        subTask.setCreatedAt(LocalDateTime.now());
        subTask = subTaskRepository.save(subTask);
        return subTaskMapper.toDTO(subTask);
    }

    @Override
    public SubTaskDTO getBySubTaskId(Long subTaskId) {
        if(subTaskId == null){
            throw new IllegalArgumentException("SubTaskId cannot be null");
        }
        SubTask subTask = subTaskRepository.findById(subTaskId).orElseThrow(() -> new IllegalArgumentException("SubTask not found"));
        return subTaskMapper.toDTO(subTask);
    }

    @Override
    public void UpdateSubTask(SubTaskDTO subTaskDTO, Long subTaskId) {
        if(subTaskId == null){
            throw new IllegalArgumentException("SubTaskId cannot be null");
        }
        if(subTaskDTO.getTitle().isBlank()){
            throw new IllegalArgumentException("Title cannot be blank");
        }
        if(subTaskDTO.getTaskId() == null){
            throw new IllegalArgumentException("TaskId cannot be null");
        }
        Task task = taskRepository.findById(subTaskDTO.getTaskId()).orElseThrow(() -> new IllegalArgumentException("Task not found"));
        if(subTaskDTO.getUserId() == null){
            throw new IllegalArgumentException("UserId cannot be null");
        }
        User user = userRepository.findById(subTaskDTO.getUserId()).orElseThrow(() -> new IllegalArgumentException("User not found"));
        roleService.get(task.getWorkspace().getId(), subTaskDTO.getUserId());

        SubTask originalSubTask = subTaskRepository.findById(subTaskId).orElseThrow(() -> new IllegalArgumentException("SubTask not found"));
        subTaskDTO.setCreatedAt(originalSubTask.getCreatedAt());
        subTaskDTO.setUpdatedAt(LocalDateTime.now());
        subTaskDTO.setId(subTaskId);

        SubTask subTask = subTaskMapper.toEntity(subTaskDTO);
        subTask.setTask(task);
        subTask.setUser(user);
        subTaskRepository.save(subTask);
    }

    @Override
    public void deleteById(Long subTaskId) {
        if(subTaskId == null){
            throw new IllegalArgumentException("SubTaskId cannot be null");
        }
        subTaskRepository.deleteById(subTaskId);
    }
}
