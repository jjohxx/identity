package edu.umss.fcyt.posgrado.identity.services;

import edu.umss.fcyt.posgrado.identity.dto.RoleDTO;
import edu.umss.fcyt.posgrado.identity.dto.UserDTO;
import edu.umss.fcyt.posgrado.identity.entities.Role;
import edu.umss.fcyt.posgrado.identity.repositories.RoleRepository;
import edu.umss.fcyt.posgrado.identity.repositories.UserRepository;
import edu.umss.fcyt.posgrado.identity.services.mapper.CustomMapper;
import edu.umss.fcyt.posgrado.identity.services.mapper.RoleMapper;
import edu.umss.fcyt.posgrado.identity.services.mapper.UserMapper;
import lombok.AllArgsConstructor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
@AllArgsConstructor
public class RoleService extends EntityService<RoleDTO, Role, Long> {

    private final RoleRepository roleRepository;
    private final UserRepository userRepository;
    private final RoleMapper roleMapper;
    private final UserMapper userMapper;

    @Override
    public Class<Role> getEntityClass() {
        return Role.class;
    }

    @Override
    public JpaRepository<Role, Long> getRepository() {
        return roleRepository;
    }

    @Override
    public CustomMapper<RoleDTO, Role> getMapper() {
        return roleMapper;
    }

    public Set<UserDTO> getUsersByRoleName(String roleName) {
        return new HashSet<>(userMapper.toDtoList(userRepository.findUsersByRoleName(roleName)));
    }
}
