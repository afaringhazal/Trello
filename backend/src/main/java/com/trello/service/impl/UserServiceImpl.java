package com.trello.service.impl;

import com.trello.domain.User;
import com.trello.exception.ServiceException;
import com.trello.repository.UserRepository;
import com.trello.service.UserService;
import com.trello.service.dto.UserDTO;
import com.trello.service.mapper.UserMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
@Transactional(rollbackFor = Exception.class)
@Slf4j
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;
    public UserServiceImpl(UserRepository userRepository, UserMapper userMapper) {
        this.userRepository = userRepository;
        this.userMapper = userMapper;
    }

    @Override
    public List<UserDTO> getAllUser() {
        List<User> users = userRepository.findAll();
        return userMapper.toDTO(users);
    }

    @Override
    public UserDTO createUser(UserDTO userDTO) {
        if(userDTO.getUsername().isBlank()){
            throw new IllegalArgumentException("Username cannot be empty or null");
        }
        if(userDTO.getEmail().isBlank()){
            throw new IllegalArgumentException("Email cannot be empty or null");
        }
        checkPass(userDTO.getPass());
        User user = userMapper.toEntity(userDTO);
        user.setCreatedAt(LocalDateTime.now());
        user = userRepository.save(user);
        return userMapper.toDTO(user);
    }

    @Override
    public UserDTO getByUsername(String username) {
        User user = userRepository.findByUsername(username).orElseThrow(() -> new IllegalArgumentException("username not found"));
        return userMapper.toDTO(user);
    }

    @Override
    public void UpdateUser(UserDTO userDTO, String username) {
        User originalUser = userRepository.findByUsername(username).orElseThrow(() -> new IllegalArgumentException("username not found"));
        if(userDTO.getUsername().isBlank()){
            throw new IllegalArgumentException("Username cannot be empty or null");
        }
        if(userDTO.getEmail().isBlank()){
            throw new IllegalArgumentException("Email cannot be empty or null");
        }
        checkPass(userDTO.getPass());
        User user = userMapper.toEntity(userDTO);
        user.setId(originalUser.getId());
        user.setUpdatedAt(LocalDateTime.now());
        user.setCreatedAt(originalUser.getCreatedAt());
        userRepository.save(user);
        return;
    }

    @Override
    public void deleteById(String username) {
        //todo check the relationship of user
        userRepository.deleteByUsername(username);
    }

    private void checkPass(String pass) {
        String regex = "^(?=.*[0-9])"
                + "(?=.*[a-z])(?=.*[A-Z])"
                + "(?=.*[@#$%^&+=])"
                + "(?=\\S+$).{8,20}$";

        // Compile the ReGex
        Pattern p = Pattern.compile(regex);
        if (pass == null) {
            throw new IllegalArgumentException("Password cannot be null");
        }
        Matcher m = p.matcher(pass);
        if(!m.matches()){
            throw new IllegalArgumentException("Password is incorrect");
        }
    }
}
