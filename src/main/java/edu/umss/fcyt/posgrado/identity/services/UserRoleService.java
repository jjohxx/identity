package edu.umss.fcyt.posgrado.identity.services;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.fge.jsonpatch.JsonPatch;
import com.github.fge.jsonpatch.JsonPatchException;
import edu.umss.fcyt.posgrado.identity.dto.UserRoleDTO;
import edu.umss.fcyt.posgrado.identity.entities.UserRole;
import edu.umss.fcyt.posgrado.identity.errors.exceptions.EntityNotFoundException;
import edu.umss.fcyt.posgrado.identity.repositories.UserRoleRepository;
import edu.umss.fcyt.posgrado.identity.services.mapper.UserRoleMapper;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class UserRoleService {

    private final ObjectMapper objectMapper = new ObjectMapper();
    private final UserRoleMapper userRoleMapper;
    private final UserRoleRepository userRoleRepository;

    /**
     * Partially updates a {@link UserRole} identified by the roleId and userId using a JsonPatch object.
     *
     * @param roleId The ID of the role to be updated.
     * @param userId The ID of the user associated with the role.
     * @param patch  The JsonPatch object containing the partial update instructions.
     * @return The updated UserRoleDTO after applying the partial update.
     * @throws EntityNotFoundException If the UserRole is not found.
     * @throws JsonProcessingException If there is an issue processing the JSON data.
     * @throws JsonPatchException      If there is an issue applying the JSON Patch.
     */
    public UserRoleDTO partiallyUpdateUserRole(Long roleId, Long userId, JsonPatch patch)
            throws JsonProcessingException, JsonPatchException {
        UserRole userRole = userRoleRepository.findByRoleIdAndUserId(roleId, userId)
                .orElseThrow(() -> new EntityNotFoundException(UserRole.class.getSimpleName()));
        UserRole patchedUserRole = applyPatchToUserRole(patch, userRole);
        return userRoleMapper.toDto(userRoleRepository.save(patchedUserRole));
    }

    private UserRole applyPatchToUserRole(JsonPatch patch, UserRole targetUserRole)
            throws JsonProcessingException, JsonPatchException {
        JsonNode patched = patch.apply(objectMapper.convertValue(targetUserRole, JsonNode.class));
        return objectMapper.treeToValue(patched, UserRole.class);
    }
}
