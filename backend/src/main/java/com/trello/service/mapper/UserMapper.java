package com.trello.service.mapper;

import com.trello.domain.Task;
import com.trello.domain.User;
import com.trello.service.dto.TaskDTO;
import com.trello.service.dto.UserDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface UserMapper extends EntityMapper<User, UserDTO> {
    @Override
    User toEntity(UserDTO userDTO);

    @Override
    UserDTO toDTO(User user);

    @Override
    List<User> toEntity(List<UserDTO> dtoList);

    @Override
    List<UserDTO> toDTO(List<User> entityList);

}
