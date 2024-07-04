package com.trello.web.rest;



import com.trello.domain.UserWorkspaceRole;
import com.trello.service.UserWorkspaceRoleService;
import com.trello.service.dto.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;
import java.util.List;


@RestController
@RequestMapping("/api/workspace/users")
@Slf4j
public class UserWorkspaceRoleResource {
    private final UserWorkspaceRoleService userWorkspaceRoleService;

    public UserWorkspaceRoleResource(UserWorkspaceRoleService userWorkspaceRoleService) {
        this.userWorkspaceRoleService = userWorkspaceRoleService;
    }

    @GetMapping("/{workspaceId}")
    public ResponseEntity<List<UserDTO>> getUsersByWorkspace(@PathVariable(name = "workspaceId") Long workspaceId) {

        return new ResponseEntity<>(userWorkspaceRoleService.getUsersByWorkspace(workspaceId), HttpStatus.OK);
    }
    @GetMapping("/{workspaceId}/{userId}")
    public ResponseEntity<UserWorkspaceRole> get(@PathVariable(name = "workspaceId") Long workspaceId, @PathVariable(name = "userId") Long userId ) {

        return new ResponseEntity<>(userWorkspaceRoleService.get(workspaceId, userId), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<UserWorkspaceRoleDTO> createWorkspace(@RequestBody @Valid UserWorkspaceRoleDTO userWorkspaceRoleDTO) {
        return ResponseEntity.status(HttpStatus.CREATED).body(userWorkspaceRoleService.create(userWorkspaceRoleDTO));
    }


    @PutMapping
    public ResponseEntity<WorkspaceDTO> updateWorkspace(@RequestBody @Valid UserWorkspaceRoleDTO userWorkspaceRoleDTO){
        userWorkspaceRoleService.Update(userWorkspaceRoleDTO);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @DeleteMapping("/{workspaceId}/{userId}")
    public ResponseEntity<Void> deleteTask(@PathVariable Long workspaceId, @PathVariable Long userId) {
        userWorkspaceRoleService.delete(workspaceId, userId);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }




}
