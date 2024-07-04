package com.trello.service.impl;

import com.trello.domain.User;
import com.trello.domain.UserWorkspaceRole;
import com.trello.repository.UserRepository;
import com.trello.repository.UserWorkspaceRoleRepository;
import com.trello.repository.WorkspaceRepository;
import com.trello.service.UserWorkspaceRoleService;
import com.trello.service.dto.UserDTO;
import com.trello.service.dto.UserWorkspaceRoleDTO;
import com.trello.service.mapper.UserMapper;
import com.trello.service.mapper.UserWorkspaceRoleMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
@Service
@Transactional(rollbackFor = Exception.class)
@Slf4j
public class UserWorkspaceRoleServiceImpl implements UserWorkspaceRoleService {
    private final UserWorkspaceRoleMapper userWorkspaceRoleMapper;
    private final UserWorkspaceRoleRepository userWorkspaceRoleRepository;
    private final UserRepository userRepository;
    private final UserMapper userMapper;
    private final WorkspaceRepository workspaceRepository;

    public UserWorkspaceRoleServiceImpl(UserWorkspaceRoleMapper userWorkspaceRoleMapper, UserWorkspaceRoleRepository userWorkspaceRoleRepository, UserRepository userRepository, UserMapper userMapper, WorkspaceRepository workspaceRepository) {
        this.userWorkspaceRoleMapper = userWorkspaceRoleMapper;
        this.userWorkspaceRoleRepository = userWorkspaceRoleRepository;
        this.userRepository = userRepository;
        this.userMapper = userMapper;
        this.workspaceRepository = workspaceRepository;
    }

    @Override
    public List<UserDTO> getUsersByWorkspace(Long workspaceId) {
        if(workspaceId == null){
            throw new IllegalArgumentException("workspaceId cannot be null");
        }
        List<UserWorkspaceRole> userWorkspace = userWorkspaceRoleRepository.findAllByWorkspaceId(workspaceId);
        List<Long> usersId = new ArrayList<>();
        userWorkspace.forEach(userWorkspaceRole -> {usersId.add(userWorkspaceRole.getUserId());});

        List<User> users = userRepository.findAllById(usersId);
        return userMapper.toDTO(users);
    }

    @Override
    public UserWorkspaceRoleDTO create(UserWorkspaceRoleDTO userWorkspaceRoleDTO) {
        if(userWorkspaceRoleDTO.getWorkspaceId() == null){
            throw new IllegalArgumentException("workspaceId cannot be null");
        }
        if(userWorkspaceRoleDTO.getUserId() == null){
            throw new IllegalArgumentException("userId cannot be null");
        }
        if(userWorkspaceRoleDTO.getRole() == null){
            throw new IllegalArgumentException("role cannot be null");
        }
        userRepository.findById(userWorkspaceRoleDTO.getUserId()).orElseThrow(() -> new IllegalArgumentException("User not found"));
        workspaceRepository.findById(userWorkspaceRoleDTO.getWorkspaceId()).orElseThrow(() -> new IllegalArgumentException("Workspace not found"));
        userWorkspaceRoleDTO.setCreatedAt(LocalDateTime.now());
        UserWorkspaceRole entity = userWorkspaceRoleMapper.toEntity(userWorkspaceRoleDTO);
        entity =userWorkspaceRoleRepository.save(entity);

        return userWorkspaceRoleMapper.toDTO(entity);
    }

    @Override
    public void Update(UserWorkspaceRoleDTO userWorkspaceRoleDTO) {
        if(userWorkspaceRoleDTO.getWorkspaceId() == null){
            throw new IllegalArgumentException("workspaceId cannot be null");
        }
        if(userWorkspaceRoleDTO.getUserId() == null){
            throw new IllegalArgumentException("userId cannot be null");
        }
        if(userWorkspaceRoleDTO.getRole() == null){
            throw new IllegalArgumentException("role cannot be null");
        }
        userRepository.findById(userWorkspaceRoleDTO.getUserId()).orElseThrow(() -> new IllegalArgumentException("User not found"));
        workspaceRepository.findById(userWorkspaceRoleDTO.getWorkspaceId()).orElseThrow(() -> new IllegalArgumentException("Workspace not found"));
        UserWorkspaceRole originalUserWork = userWorkspaceRoleRepository.findById(new UserWorkspaceRole.UserWorkspaceRolePK(userWorkspaceRoleDTO.getUserId(),
                userWorkspaceRoleDTO.getWorkspaceId())).orElseThrow(() -> new IllegalArgumentException("UserWorkspaceRole not found"));

        originalUserWork.setRole(userWorkspaceRoleDTO.getRole());
        originalUserWork.setUpdatedAt(LocalDateTime.now());
         userWorkspaceRoleRepository.save(originalUserWork);
         return;

    }

    @Override
    public void delete(Long workspaceId, Long userId) {
        if(workspaceId == null){
            throw new IllegalArgumentException("workspaceId cannot be null");
        }
        if(userId == null){
            throw new IllegalArgumentException("userId cannot be null");
        }
        userWorkspaceRoleRepository.deleteById(new UserWorkspaceRole.UserWorkspaceRolePK(workspaceId, userId));
    }

    @Override
    public UserWorkspaceRole get(Long workspaceId, Long userId) {
        if(workspaceId == null){
            throw new IllegalArgumentException("workspace id is null!");
        }
        if(userId == null){
            throw new IllegalArgumentException("user id is null");
        }
        UserWorkspaceRole userWorkspaceRoleNotFound = userWorkspaceRoleRepository.findById(new UserWorkspaceRole.UserWorkspaceRolePK(userId, workspaceId)).
                orElseThrow(() -> new IllegalArgumentException("UserWorkspaceRole not found"));
        return userWorkspaceRoleNotFound;
    }
}
