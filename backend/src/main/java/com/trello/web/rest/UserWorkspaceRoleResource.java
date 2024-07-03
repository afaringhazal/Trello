package com.trello.web.rest;



import com.trello.service.UserWorkspaceRoleService;
import com.trello.service.dto.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;
import java.util.List;


@RestController
@RequestMapping("/api")
@Slf4j
public class UserWorkspaceRoleResource {
    private final UserWorkspaceRoleService userWorkspaceRoleService;

    public UserWorkspaceRoleResource(UserWorkspaceRoleService userWorkspaceRoleService) {
        this.userWorkspaceRoleService = userWorkspaceRoleService;
    }

    @GetMapping("/workspace/users/{workspaceId}")
    public ResponseEntity<List<UserDTO>> getUsersByWorkspace(@PathVariable(name = "workspaceId") Long workspaceId) {

        return new ResponseEntity<>(userWorkspaceRoleService.getUsersByWorkspace(workspaceId), HttpStatus.OK);
    }

    @PostMapping("/workspace/users")
    public ResponseEntity<UserWorkspaceRoleDTO> createWorkspace(@RequestBody @Valid UserWorkspaceRoleDTO userWorkspaceRoleDTO) {
        return ResponseEntity.status(HttpStatus.CREATED).body(userWorkspaceRoleService.create(userWorkspaceRoleDTO));
    }


    @PutMapping("/workspace/users")
    public ResponseEntity<WorkspaceDTO> updateWorkspace(@RequestBody @Valid UserWorkspaceRoleDTO userWorkspaceRoleDTO){
        userWorkspaceRoleService.Update(userWorkspaceRoleDTO);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @DeleteMapping("/workspace/users/{workspaceId}/{userId}")
    public ResponseEntity<Void> deleteTask(@PathVariable Long workspaceId, @PathVariable Long userId) {
        userWorkspaceRoleService.delete(workspaceId, userId);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }




}
