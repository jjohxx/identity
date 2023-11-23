package edu.umss.fcyt.posgrado.identity.services.mapper;

import edu.umss.fcyt.posgrado.identity.dto.RoleDTO;
import edu.umss.fcyt.posgrado.identity.entities.Role;
import org.springframework.stereotype.Component;

@Component
public class RoleMapper implements CustomMapper<RoleDTO, Role> {

    @Override
    public RoleDTO toDto(Role entity) {
        RoleDTO roleDTO = new RoleDTO();
        roleDTO.setId(entity.getId());
        roleDTO.setName(entity.getName());
        roleDTO.setDescription(entity.getDescription());
        return roleDTO;
    }

    @Override
    public Role toEntity(RoleDTO roleDTO) {
        Role role = new Role();
        role.setId(roleDTO.getId());
        role.setName(roleDTO.getName());
        role.setDescription(roleDTO.getDescription());
        return role;
    }
}
