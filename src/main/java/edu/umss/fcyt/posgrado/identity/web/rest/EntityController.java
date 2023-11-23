package edu.umss.fcyt.posgrado.identity.web.rest;

import edu.umss.fcyt.posgrado.identity.dto.BaseDTO;
import edu.umss.fcyt.posgrado.identity.entities.EntityID;
import edu.umss.fcyt.posgrado.identity.services.EntityService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;

public abstract class EntityController<DTO extends BaseDTO<ID>, T extends EntityID<Long>, ID> {

    public abstract EntityService<DTO, T, ID> getEntityService();

    @PostMapping
    public ResponseEntity<DTO> save(@RequestBody @Valid final DTO dto) {
        dto.setId(null);
        final DTO entityDTO = getEntityService().create(dto);
        final URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(entityDTO.getId())
                .toUri();
        return ResponseEntity
                .created(location)
                .body(entityDTO);
    }

    @GetMapping
    public ResponseEntity<List<DTO>> getAll(@RequestParam(value = "detailed", required = false,
            defaultValue = "false") boolean detailed) {
        return ResponseEntity
                .ok()
                .body(getEntityService().findAll(detailed));
    }

    @GetMapping("/{id}")
    public ResponseEntity<DTO> getById(@PathVariable final ID id) {
        return ResponseEntity
                .ok()
                .body(getEntityService().findById(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<DTO> update(@RequestBody @Valid final DTO dto,
                                      @PathVariable final ID id) {
        dto.setId(id);
        return ResponseEntity.ok().body(getEntityService().create(dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable final ID id) {
        getEntityService().deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
