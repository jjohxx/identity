package edu.umss.fcyt.posgrado.identity.web.rest;

import edu.umss.fcyt.posgrado.identity.dto.RoleDTO;
import edu.umss.fcyt.posgrado.identity.entities.User;
import edu.umss.fcyt.posgrado.identity.dto.UserDTO;
import edu.umss.fcyt.posgrado.identity.dto.UserDetailDTO;
import edu.umss.fcyt.posgrado.identity.services.EntityService;
import edu.umss.fcyt.posgrado.identity.services.UserDetailService;
import edu.umss.fcyt.posgrado.identity.services.UserService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("/v1/users")
@AllArgsConstructor
public class UserController extends EntityController<UserDTO, User, Long> {

    private final UserService userService;
    private final UserDetailService userDetailService;

    @Override
    public EntityService<UserDTO, User, Long> getEntityService() {
        return userService;
    }

    /**
     * Saves the {@link UserDetailDTO} mapping to UserDetail entity for a given user ID.
     *
     * @param dto The {@link UserDetailDTO} containing the details to be saved
     * @param id The ID of the user
     * @return ResponseEntity containing the saved UserDetailDTO
     */
    @PostMapping("/{id}/details")
    public ResponseEntity<UserDetailDTO> save(@RequestBody @Valid final UserDetailDTO dto,
                                              @PathVariable Long id) {
        userService.findById(id); // Make sure that the user is found, else throw EntityNotFoundException.
        final UserDetailDTO entityDTO = userDetailService.create(dto);
        final URI location = userService.buildUriForEntity(id);
        return ResponseEntity
                .created(location)
                .body(entityDTO);
    }

    /**
     * Deletes a user by marking it as soft deleted.
     *
     * @param id The ID of the user to be soft deleted
     * @return ResponseEntity with no content on successful deletion
     */
    @DeleteMapping("/{id}")
    @Override
    public ResponseEntity<Void> delete(@PathVariable final Long id) {
        userService.softDeleteUser(id);
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/{userId}/roles")
    public ResponseEntity<List<RoleDTO>> assignRolesToUser(@PathVariable Long userId, @RequestBody Set<Long> roleIds) {
        return ResponseEntity
                .ok()
                .body(userService.assignRolesToUser(userId, roleIds));
    }

}
