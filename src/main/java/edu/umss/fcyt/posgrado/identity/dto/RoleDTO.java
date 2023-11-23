package edu.umss.fcyt.posgrado.identity.dto;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Getter
@Setter
public class RoleDTO extends BaseDTO<Long> {

    @NotNull
    @NotEmpty
    private String name;
    private String description;
}
