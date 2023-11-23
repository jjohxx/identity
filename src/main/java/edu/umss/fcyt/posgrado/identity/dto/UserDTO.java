package edu.umss.fcyt.posgrado.identity.dto;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Max;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
public class UserDTO extends BaseDTO<Long> {

    @NotNull
    @NotBlank
    @Max(value = 150)
    private String username;

    @NotNull
    @NotBlank
    @Max(value = 150)
    private String password;

    @NotNull
    @NotBlank
    @Max(value = 150)
    private String email;

    private boolean deleted;

    private LocalDateTime createdAt;

    private UserDetailDTO userDetailDTO;

    private List<RoleDTO> roles;
}
