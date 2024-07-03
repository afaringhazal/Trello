package com.trello.web.rest;
import com.trello.service.UserService;
import com.trello.service.dto.UserDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;
import java.util.List;


@RestController
@RequestMapping("/api")
@Slf4j
public class UserResource {
    private final UserService userService;

    public UserResource(UserService userService) {
        this.userService= userService;
    }

    @GetMapping("/me")
    public ResponseEntity<UserDTO> me(@AuthenticationPrincipal UserDetails userDetails) {

        return new ResponseEntity<>(userService.getByUsername(userDetails.getUsername()), HttpStatus.OK);
    }

    @GetMapping("/user")
    public ResponseEntity<List<UserDTO>> getAllUser() {

        return new ResponseEntity<>(userService.getAllUser(), HttpStatus.OK);
    }

    @PostMapping("/user")
    public ResponseEntity<UserDTO> createWorkspace(@RequestBody @Valid UserDTO userDTO) {
        return ResponseEntity.status(HttpStatus.CREATED).body(userService.createUser(userDTO));
    }

    @GetMapping("/user/{username}")
    public ResponseEntity<UserDTO> getByTaskId(@PathVariable(name = "username") String username) {

        return new ResponseEntity<>(userService.getByUsername(username), HttpStatus.OK);
    }


    @PutMapping("/user/{username}")
    public ResponseEntity<UserDTO> updateUser(@PathVariable(name = "username") String username, @RequestBody @Valid UserDTO userDTO){
        userService.UpdateUser(userDTO, username);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @DeleteMapping("/user/{username}")
    public ResponseEntity<Void> deleteUser(@PathVariable String username) {
        userService.deleteById(username);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

}
