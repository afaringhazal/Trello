package com.trello.web.rest;


import com.trello.service.WorkspaceService;
import com.trello.service.dto.WorkspaceDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.text.ParseException;
import java.util.List;


@RestController
@RequestMapping("/api")
@Slf4j
public class WorkspaceResource {

    private final WorkspaceService workspaceService;

    public WorkspaceResource(WorkspaceService workspaceService) {
        this.workspaceService = workspaceService;
    }

    @GetMapping("/workspace")
    public ResponseEntity<List<WorkspaceDTO>> getAllWorkspace() {

        return new ResponseEntity<>(workspaceService.getAllWorkspace(), HttpStatus.OK);
    }

    @PostMapping("/workspace")
    public ResponseEntity<WorkspaceDTO> createWorkspace(@RequestBody @Valid WorkspaceDTO workspaceDTO) {
        return ResponseEntity.status(HttpStatus.CREATED).body(workspaceService.createWorkspace(workspaceDTO));
    }

    @GetMapping("/workspace/{workspaceId}")
    public ResponseEntity<WorkspaceDTO> getByWorkspaceId(@PathVariable(name = "workspaceId") Long workspaceId) {

        return new ResponseEntity<>(workspaceService.getByWorkspaceId(workspaceId), HttpStatus.OK);
    }



    @PutMapping("/workspace/{workspaceId}")
    public ResponseEntity<WorkspaceDTO> updateWorkspace(@PathVariable(name = "workspaceId") Long workspaceId, @RequestBody @Valid WorkspaceDTO workspaceDTO) throws ParseException {
        workspaceService.UpdateWorkspace(workspaceDTO, workspaceId);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @DeleteMapping("/workspace/{workspaceId}")
    public ResponseEntity<Void> deleteWorkspace(@PathVariable Long workspaceId) {
        workspaceService.deleteById(workspaceId);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }



}
