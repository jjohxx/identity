package edu.umss.fcyt.posgrado.identity.errors;

import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;

import java.util.Collections;
import java.util.List;

@Getter
@Setter
public class CustomError {

    private HttpStatus status;
    private String message;
    private List<String> errors;

    public CustomError(HttpStatus status, String message, List<String> errors) {
        super();
        this.status = status;
        this.message = message;
        this.errors = errors;
    }

    public CustomError(HttpStatus status, String message, String error) {
        super();
        this.status = status;
        this.message = message;
        errors = Collections.singletonList(error);
    }
    public CustomError(HttpStatus status, String message) {
        super();
        this.status = status;
        this.message = message;
        errors = Collections.emptyList();
    }
}
