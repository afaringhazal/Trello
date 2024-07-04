package com.trello.web.rest;



import com.trello.service.SubTaskService;
import com.trello.service.TaskService;
import com.trello.service.dto.SubTaskDTO;
import com.trello.service.dto.TaskDTO;
import com.trello.service.dto.WorkspaceDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;
import java.util.List;


@RestController
@RequestMapping("/api/sub_task")
@Slf4j
public class SubTaskResource {
    private final SubTaskService subTaskService;

    public SubTaskResource(SubTaskService subTaskService) {
        this.subTaskService = subTaskService;
    }


    @GetMapping("/by_task_id/{taskId}")
    public ResponseEntity<List<SubTaskDTO>> getSubTasksByTaskId(@PathVariable(name = "taskId") Long taskId) {

        return new ResponseEntity<>(subTaskService.getSubTasksByTaskId(taskId), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<SubTaskDTO> createWorkspace(@RequestBody @Valid SubTaskDTO subTaskDTO) {
        return ResponseEntity.status(HttpStatus.CREATED).body(subTaskService.createSubTask(subTaskDTO));
    }

    @GetMapping("/{subTaskId}")
    public ResponseEntity<SubTaskDTO> getBySubTaskId(@PathVariable(name = "subTaskId") Long subTaskId) {
        return new ResponseEntity<>(subTaskService.getBySubTaskId(subTaskId), HttpStatus.OK);
    }


    @PutMapping("/{subTaskId}")
    public ResponseEntity<WorkspaceDTO> updateWorkspace(@PathVariable(name = "subTaskId") Long subTaskId, @RequestBody @Valid SubTaskDTO subTaskDTO){
        subTaskService.UpdateSubTask(subTaskDTO, subTaskId);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @DeleteMapping("/{subTaskId}")
    public ResponseEntity<Void> deleteTask(@PathVariable Long subTaskId) {
        subTaskService.deleteById(subTaskId);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }




}
