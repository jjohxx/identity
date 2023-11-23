package edu.umss.fcyt.posgrado.identity.services;

import edu.umss.fcyt.posgrado.identity.errors.exceptions.EntityNotFoundException;
import edu.umss.fcyt.posgrado.identity.services.mapper.CustomMapper;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public abstract class EntityService<DTO, T, ID> {

    public abstract Class<T> getEntityClass();

    public abstract JpaRepository<T, ID> getRepository();

    public abstract CustomMapper<DTO, T> getMapper();

    public DTO create(final DTO dto) {
        return getMapper()
                .toDto(getRepository().save(getMapper().toEntity(dto)));
    }

    public List<DTO> findAll(final boolean detailed) {
        List<T> all = detailed ? getRepository().findAll() : getSummarizedAll();
        return getMapper().toDtoList(all);
    }

    public DTO findById(final ID id) {
        T entity = getRepository()
                .findById(id)
                .orElseThrow(() -> new EntityNotFoundException(getEntityClass().getSimpleName()));
        return getMapper().toDto(entity);
    }

    public void deleteById(final ID id) {
        Optional
                .of(findById(id))
                .ifPresent(t -> getRepository().deleteById(id));
    }

    public List<T> getSummarizedAll() {
        return getRepository().findAll();
    }
}
