package edu.umss.fcyt.posgrado.identity.dto;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;
import java.time.LocalDate;

@Getter
@Setter
public class UserRoleDTO extends BaseDTO<Long> {

    @NotNull
    private boolean active;

    private LocalDate createdAt;

    @NotNull
    private Long userId;

    @NotNull
    private Long roleId;
}
