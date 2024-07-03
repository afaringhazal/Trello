package com.trello.service.impl;

import com.trello.domain.SubTask;
import com.trello.domain.Task;
import com.trello.repository.SubTaskRepository;
import com.trello.repository.TaskRepository;
import com.trello.service.SubTaskService;
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

    public SubTaskServiceImpl(SubTaskRepository subTaskRepository, SubTaskMapper subTaskMapper, TaskRepository taskRepository) {
        this.subTaskRepository = subTaskRepository;
        this.subTaskMapper = subTaskMapper;
        this.taskRepository = taskRepository;
    }


    @Override
    public List<SubTaskDTO> getSubTasksByTaskId(Long taskId) {
        List<SubTask> subtasks = subTaskRepository.findAllByTask(taskId);
        return subTaskMapper.toDTO(subtasks);
    }

    @Override
    public SubTaskDTO createSubTask(SubTaskDTO subTaskDTO) {
        if(subTaskDTO.getTitle().isBlank()){
            throw new IllegalArgumentException("Title cannot be blank");
        }
        if(subTaskDTO.getTaskId() == null){
            throw new IllegalArgumentException("TaskId cannot be null");
        }
        SubTask subTask = subTaskMapper.toEntity(subTaskDTO);
        Task task = taskRepository.findById(subTask.getTask().getId()).orElseThrow(() -> new IllegalArgumentException("Task not found"));
        subTask.setTask(task);
        // TODO assignee_id must be a valid reference to an existing record in the User table within the same workspace
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
        SubTask subTask = subTaskMapper.toEntity(subTaskDTO);
        Task task = taskRepository.findById(subTask.getTask().getId()).orElseThrow(() -> new IllegalArgumentException("Task not found"));
        subTask.setTask(task);
        // TODO assignee_id must be a valid reference to an existing record in the User table within the same workspace
        subTask.setId(subTaskId);
        subTask.setUpdatedAt(LocalDateTime.now());
        subTaskRepository.save(subTask);
        return;
    }

    @Override
    public void deleteById(Long subTaskId) {
        if(subTaskId == null){
            throw new IllegalArgumentException("SubTaskId cannot be null");
        }
        subTaskRepository.deleteById(subTaskId);
    }
}
