package edu.umss.fcyt.posgrado.identity.dto;

import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.Max;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

@Getter
@Setter
public class UserDetailDTO extends BaseDTO<Long> {

    @NotNull
    @NotBlank
    @Max(value = 100)
    private String firstName;

    @NotNull
    @NotBlank
    @Max(value = 100)
    private String lastName;

    private Integer age;

    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDate birthDay;

}
