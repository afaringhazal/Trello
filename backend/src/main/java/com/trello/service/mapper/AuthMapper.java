package com.trello.service.mapper;

import com.trello.domain.User;
import com.trello.service.dto.RegisterUserDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface AuthMapper extends EntityMapper<User, RegisterUserDTO> {
    @Override
    @Mapping(source = "email", target = "email")
    User toEntity(RegisterUserDTO registerUserDTO);

    @Override
    @Mapping(source = "email", target = "email")
    RegisterUserDTO toDTO(User user);

    @Override
    @Mapping(source = "email", target = "email")
    List<User> toEntity(List<RegisterUserDTO> dtoList);

    @Override
    @Mapping(source = "email", target = "email")
    List<RegisterUserDTO> toDTO(List<User> entityList);

}
