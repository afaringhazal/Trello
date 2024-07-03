package com.trello.web.rest;

import javax.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.trello.service.TaskService;
import com.trello.service.dto.TaskDTO;
import com.trello.service.dto.WorkspaceDTO;

import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/api")
@Slf4j
public class TaskResource {
    private final TaskService taskService;

    public TaskResource(TaskService taskService) {
        this.taskService = taskService;
    }

    @GetMapping("/task/{workspaceId}")
    public ResponseEntity<TaskDTO> getTasksByWorkspaceId(@PathVariable(name = "workspaceId") Long workspaceId) {

        return new ResponseEntity<>(taskService.getTasksByWorkspaceId(workspaceId), HttpStatus.OK);
    }

    @PostMapping("/task")
    public ResponseEntity<TaskDTO> createWorkspace(@RequestBody @Valid TaskDTO taskDTO) {
        return ResponseEntity.status(HttpStatus.CREATED).body(taskService.createTask(taskDTO));
    }

    @GetMapping("/task/{taskId}")
    public ResponseEntity<TaskDTO> getByTaskId(@PathVariable(name = "taskId") Long taskId) {

        return new ResponseEntity<>(taskService.getByTaskId(taskId), HttpStatus.OK);
    }

    @PutMapping("/task/{taskId}")
    public ResponseEntity<WorkspaceDTO> updateWorkspace(@PathVariable(name = "taskId") Long taskId,
            @RequestBody @Valid TaskDTO taskDTO) {
        taskService.Updatetask(taskDTO, taskId);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @DeleteMapping("/task/{taskId}")
    public ResponseEntity<Void> deleteTask(@PathVariable Long taskId) {
        taskService.deleteById(taskId);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
