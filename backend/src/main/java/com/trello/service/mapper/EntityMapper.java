package com.trello.service.mapper;

import java.util.List;

public interface EntityMapper<ENTITY, DTO> {
    ENTITY toEntity(DTO dto);

    DTO toDTO(ENTITY entity);

    List<ENTITY> toEntity(List<DTO> dtoList);

    List<DTO> toDTO(List<ENTITY> entityList);
}
