package com.trello.web.rest;

import java.text.ParseException;
import java.util.List;

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

import com.trello.service.WorkspaceService;
import com.trello.service.dto.WorkspaceDTO;

import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/api/workspace")
@Slf4j
public class WorkspaceResource {

    private final WorkspaceService workspaceService;

    public WorkspaceResource(WorkspaceService workspaceService) {
        this.workspaceService = workspaceService;
    }

    @GetMapping
    public ResponseEntity<List<WorkspaceDTO>> getAllWorkspace() {

        return new ResponseEntity<>(workspaceService.getAllWorkspace(), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<WorkspaceDTO> createWorkspace(@RequestBody @Valid WorkspaceDTO workspaceDTO) {
        return ResponseEntity.status(HttpStatus.CREATED).body(workspaceService.createWorkspace(workspaceDTO));
    }

    @GetMapping("/{workspaceId}")
    public ResponseEntity<WorkspaceDTO> getByWorkspaceId(@PathVariable(name = "workspaceId") Long workspaceId) {

        return new ResponseEntity<>(workspaceService.getByWorkspaceId(workspaceId), HttpStatus.OK);
    }

    @PutMapping("/{workspaceId}")
    public ResponseEntity<WorkspaceDTO> updateWorkspace(@PathVariable(name = "workspaceId") Long workspaceId,
            @RequestBody @Valid WorkspaceDTO workspaceDTO) throws ParseException {
        workspaceService.UpdateWorkspace(workspaceDTO, workspaceId);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @DeleteMapping("/{workspaceId}")
    public ResponseEntity<Void> deleteWorkspace(@PathVariable Long workspaceId) {
        workspaceService.deleteById(workspaceId);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

}
