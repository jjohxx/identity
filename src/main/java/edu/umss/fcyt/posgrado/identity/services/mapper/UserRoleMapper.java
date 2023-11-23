package edu.umss.fcyt.posgrado.identity.services.mapper;

import edu.umss.fcyt.posgrado.identity.dto.UserRoleDTO;
import edu.umss.fcyt.posgrado.identity.entities.UserRole;
import org.springframework.stereotype.Component;

@Component
public class UserRoleMapper implements CustomMapper<UserRoleDTO, UserRole> {

    @Override
    public UserRoleDTO toDto(UserRole entity) {
        UserRoleDTO userRoleDTO = new UserRoleDTO();
        userRoleDTO.setId(entity.getId());
        userRoleDTO.setActive(entity.isActive());
        userRoleDTO.setCreatedAt(entity.getCreatedAt());
        userRoleDTO.setUserId(entity.getUserId());
        userRoleDTO.setRoleId(entity.getRoleId());
        return userRoleDTO;
    }

    @Override
    public UserRole toEntity(UserRoleDTO userRoleDTO) {
        UserRole userRole = new UserRole();
        userRole.setId(userRoleDTO.getId());
        userRole.setActive(userRoleDTO.isActive());
        userRole.setCreatedAt(userRoleDTO.getCreatedAt());
        userRole.setUserId(userRole.getUserId());
        userRole.setRoleId(userRole.getRoleId());
        return userRole;
    }
}
