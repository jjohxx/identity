package edu.umss.fcyt.posgrado.identity.services.mapper;

import java.util.Collection;
import java.util.List;

public interface CustomMapper<DTO, T> {
    DTO toDto(T entity);

    T toEntity(DTO dto);

    default List<DTO> toDtoList(Collection<T> entities) {
        return entities.stream().map(this::toDto).toList();
    }

    default List<T> toEntityList(Collection<DTO> dtos) {
        return dtos.stream().map(this::toEntity).toList();
    }
}
