package edu.umss.fcyt.posgrado.identity.services;

import edu.umss.fcyt.posgrado.identity.dto.RoleDTO;
import edu.umss.fcyt.posgrado.identity.dto.UserDTO;
import edu.umss.fcyt.posgrado.identity.entities.Role;
import edu.umss.fcyt.posgrado.identity.entities.User;
import edu.umss.fcyt.posgrado.identity.entities.UserDetail;
import edu.umss.fcyt.posgrado.identity.errors.exceptions.EntityNotFoundException;
import edu.umss.fcyt.posgrado.identity.repositories.RoleRepository;
import edu.umss.fcyt.posgrado.identity.repositories.UserDetailRepository;
import edu.umss.fcyt.posgrado.identity.repositories.UserRepository;
import edu.umss.fcyt.posgrado.identity.services.mapper.CustomMapper;
import edu.umss.fcyt.posgrado.identity.services.mapper.RoleMapper;
import edu.umss.fcyt.posgrado.identity.services.mapper.UserDetailMapper;
import edu.umss.fcyt.posgrado.identity.services.mapper.UserMapper;
import edu.umss.fcyt.posgrado.identity.utils.SummarizedFieldsProcessor;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.*;

@Service
@Transactional
@AllArgsConstructor
public class UserService extends EntityService<UserDTO, User, Long> {

    private final UserRepository userRepository;
    private final UserDetailRepository userDetailRepository;
    private final UserDetailMapper userDetailMapper;
    private final RoleRepository roleRepository;
    private final UserMapper userMapper;
    private final RoleMapper roleMapper;
    private final SummarizedFieldsProcessor summarizedFieldsProcessor;

    @Override
    public Class<User> getEntityClass() {
        return User.class;
    }

    @Override
    public JpaRepository<User, Long> getRepository() {
        return userRepository;
    }

    @Override
    public CustomMapper<UserDTO, User> getMapper() {
        return userMapper;
    }

    /**
     * Retrieves a list of users with summarized fields based on the defined logic.
     * Summarized fields are processed and set to null in each user object.
     *
     * @return A list of users with summarized fields set to null.
     */
    @Override
    public List<User> getSummarizedAll() {
        List<User> users = userRepository.findAll();
        summarizedFieldsProcessor.processFields(users);
        return users;
    }

    /**
     * Creates a new {@link User} based on the provided UserDTO, including its associated user details if present.
     *
     * @param userDTO The  {@link UserDTO} containing user information to be created.
     * @return The created  {@link UserDTO}, including associated user details if provided.
     */
    @Override
    public UserDTO create(UserDTO userDTO) {
        final UserDTO createdUserDTO = super.create(userDTO);

        if (userDTO.getUserDetailDTO() != null) {
            final UserDetail userDetail
                    = userDetailRepository.save(userDetailMapper.toEntity(userDTO.getUserDetailDTO()));
            createdUserDTO.setUserDetailDTO(userDetailMapper.toDto(userDetail));
        }

        return createdUserDTO;
    }

    /**
     * Soft deletes a {@link User} by setting the 'deleted' flag to true.
     *
     * @param userId The ID of the user to be soft deleted
     */
    public void softDeleteUser(Long userId) {
        UserDTO userDTO = findById(userId);
        userDTO.setDeleted(true);
        userRepository.save(getMapper().toEntity(userDTO));
    }

    /**
     * Assigns multiple roles to a user identified by the provided user ID.
     *
     * @param userId   The ID of the user to whom roles will be assigned.
     * @param roleIds  A set containing IDs of roles to be assigned.
     * @return A list of RoleDTOs representing the roles assigned to the user after the operation.
     * @throws EntityNotFoundException If the user with the given ID does not exist.
     */
    public List<RoleDTO> assignRolesToUser(Long userId, Set<Long> roleIds) {
        final User user = userRepository
                .findById(userId)
                .orElseThrow(() -> new EntityNotFoundException(getEntityClass().getSimpleName()));
        final List<Role> roles = roleRepository.findAllById(roleIds);
        user.getRoles().addAll(roles);
        return roleMapper.toDtoList(userRepository.save(user).getRoles());
    }

    public URI buildUriForEntity(Long userId) {
        return ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(userId)
                .toUri();
    }


}
