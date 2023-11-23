package edu.umss.fcyt.posgrado.identity.web.rest;

import edu.umss.fcyt.posgrado.identity.dto.RoleDTO;
import edu.umss.fcyt.posgrado.identity.dto.UserDetailDTO;
import edu.umss.fcyt.posgrado.identity.dto.UserDTO;
import edu.umss.fcyt.posgrado.identity.services.UserDetailService;
import edu.umss.fcyt.posgrado.identity.services.UserService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.ResponseEntity;

import java.net.URI;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class UserControllerTest {

    @Mock
    private UserService userService;

    @Mock
    private UserDetailService userDetailService;

    @InjectMocks
    private UserController userController;

    @Test
    void delete_ReturnsNoContentResponse() {
        // Given
        Long userId = 1L;
        doNothing().when(userService).softDeleteUser(userId);

        // When
        ResponseEntity<Void> responseEntity = userController.delete(userId);

        // Then
        assertEquals(204, responseEntity.getStatusCodeValue());
    }

    @Test
    void getById_ReturnsUserById() {
        // Given
        Long userId = 1L;
        UserDTO expectedUser = new UserDTO();
        when(userService.findById(userId)).thenReturn(expectedUser);

        // When
        ResponseEntity<UserDTO> responseEntity = userController.getById(userId);

        // Then
        assertEquals(expectedUser, responseEntity.getBody());
    }

    @Test
    void update_ReturnsUpdatedUser() {
        // Given
        Long userId = 1L;
        UserDTO updatedUser = new UserDTO();
        updatedUser.setId(userId);
        when(userService.create(updatedUser)).thenReturn(updatedUser);

        // When
        ResponseEntity<UserDTO> responseEntity = userController.update(updatedUser, userId);

        // Then
        assertEquals(updatedUser, responseEntity.getBody());
    }

    @Test
    void assignRolesToUser_ReturnsRolesAssigned() {
        // Given
        Long userId = 1L;
        Set<Long> roleIds = new HashSet<>(Collections.singletonList(1L));
        List<RoleDTO> expectedRoles = Collections.singletonList(new RoleDTO());
        when(userService.assignRolesToUser(userId, roleIds)).thenReturn(expectedRoles);

        // When
        ResponseEntity<List<RoleDTO>> responseEntity = userController.assignRolesToUser(userId, roleIds);

        // Then
        assertEquals(expectedRoles, responseEntity.getBody());
    }

    @Test
    void save_UserDetail_ReturnsCreatedResponse() {
        // Given
        Long userId = 1L;
        UserDetailDTO userDetailDTO = new UserDetailDTO();
        userDetailDTO.setId(1L);

        // Mocking userService to return a UserDTO
        when(userService.findById(userId)).thenReturn(new UserDTO());
        when(userService.buildUriForEntity(userId)).thenReturn(URI.create("http://localhost:8080/v1/users/1"));

        // Mocking userDetailService behavior
        when(userDetailService.create(any(UserDetailDTO.class))).thenReturn(userDetailDTO);


        // When
        ResponseEntity<UserDetailDTO> responseEntity = userController.save(userDetailDTO, userId);

        // Then
        assertEquals(userDetailDTO, responseEntity.getBody());
    }
}
