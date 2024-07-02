package com.trello.service;

import com.trello.service.dto.UserDTO;

import java.util.List;

public interface UserService {
    List<UserDTO> getAllUser();

    UserDTO createUser(UserDTO userDTO);

    UserDTO getByUsername(String username);

    void UpdateUser(UserDTO userDTO, String username);

    void deleteById(String username);
}
