package edu.umss.fcyt.posgrado.identity.web.rest;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.fge.jsonpatch.JsonPatch;
import edu.umss.fcyt.posgrado.identity.dto.RoleDTO;
import edu.umss.fcyt.posgrado.identity.dto.UserRoleDTO;
import edu.umss.fcyt.posgrado.identity.services.RoleService;
import edu.umss.fcyt.posgrado.identity.services.UserRoleService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class RoleControllerTest {

    @Mock
    private UserRoleService userRoleService;

    @Mock
    private RoleService entityService;

    @InjectMocks
    private RoleController roleController;

    @Test
    void testUpdateUserRolePartially() throws Exception {
        // Given
        Long roleId = 1L;
        Long userId = 2L;
        //    {
        //        "op":"replace",
        //        "path": "/active",
        //        "value": "false"
        //    }
        JsonPatch patch = createJsonPatch("replace", "/active", "false");

        // Simulate the service behavior when partially updating user role
        when(userRoleService.partiallyUpdateUserRole(roleId, userId, patch)).thenReturn(new UserRoleDTO());

        // When
        ResponseEntity<UserRoleDTO> response = roleController.updateUserRolePartially(roleId, userId, patch);

        // Then
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
    }

    @Test
    void delete_ReturnsNoContentResponse() {
        // Given
        Long roleId = 1L;
        doNothing().when(entityService).deleteById(roleId); // Mocking void method

        // When
        ResponseEntity<Void> responseEntity = roleController.delete(roleId);

        // Then
        assertEquals(204, responseEntity.getStatusCodeValue());
    }

    @Test
    void getById_ReturnsRoleById() {
        // Given
        Long roleId = 1L;
        RoleDTO expectedRole = new RoleDTO();
        when(entityService.findById(roleId)).thenReturn(expectedRole);

        // When
        ResponseEntity<RoleDTO> responseEntity = roleController.getById(roleId);

        // Then
        assertEquals(expectedRole, responseEntity.getBody());
    }

    @Test
    void update_ReturnsUpdatedRole() {
        // Given
        Long roleId = 1L;
        RoleDTO updatedRole = new RoleDTO();
        updatedRole.setId(roleId);
        when(entityService.create(updatedRole)).thenReturn(updatedRole);

        // When
        ResponseEntity<RoleDTO> responseEntity = roleController.update(updatedRole, roleId);

        // Then
        assertEquals(updatedRole, responseEntity.getBody());
    }

    private JsonPatch createJsonPatch(String op, String path, String value) {
        try {
            JsonNode jsonNode = new ObjectMapper().createObjectNode().put("op", op).put("path", path).put("value", value);
            return JsonPatch.fromJson(jsonNode);
        } catch (IOException ex) {
            return null;
        }
    }
}

