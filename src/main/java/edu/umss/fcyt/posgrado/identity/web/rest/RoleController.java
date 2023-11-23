package edu.umss.fcyt.posgrado.identity.web.rest;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.github.fge.jsonpatch.JsonPatch;
import com.github.fge.jsonpatch.JsonPatchException;
import edu.umss.fcyt.posgrado.identity.dto.RoleDTO;
import edu.umss.fcyt.posgrado.identity.dto.UserDTO;
import edu.umss.fcyt.posgrado.identity.dto.UserRoleDTO;
import edu.umss.fcyt.posgrado.identity.entities.Role;
import edu.umss.fcyt.posgrado.identity.services.EntityService;
import edu.umss.fcyt.posgrado.identity.services.RoleService;
import edu.umss.fcyt.posgrado.identity.services.UserRoleService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

@RestController
@RequestMapping("/v1/roles")
@AllArgsConstructor
public class RoleController extends EntityController<RoleDTO, Role, Long> {

    private final RoleService roleService;
    private final UserRoleService userRoleService;

    @Override
    public EntityService<RoleDTO, Role, Long> getEntityService() {
        return roleService;
    }

//    {
//        "op":"replace",
//        "path": "/active",
//        "value": "false"
//    }
    @PatchMapping(value = "/{roleId}/users/{userId}", consumes = "application/json-patch+json")
    public ResponseEntity<UserRoleDTO> updateUserRolePartially(@PathVariable Long roleId,
                                                               @PathVariable Long userId,
                                                               @RequestBody JsonPatch patch) throws JsonPatchException, JsonProcessingException {
        return ResponseEntity.ok().body(userRoleService.partiallyUpdateUserRole(roleId, userId, patch));
    }

    @GetMapping("/{roleName}/users")
    public ResponseEntity<Set<UserDTO>> getUsersByRole(@PathVariable String roleName) {
        Set<UserDTO> users = roleService.getUsersByRoleName(roleName);
        return ResponseEntity.ok(users);
    }

}
