package edu.umss.fcyt.posgrado.identity.errors;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.github.fge.jsonpatch.JsonPatchException;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import static org.springframework.http.HttpStatus.BAD_REQUEST;
import static org.springframework.http.HttpStatus.NOT_FOUND;

@ControllerAdvice
public class GlobalErrorHandler {

    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity<CustomError> handleEntityNotFoundException(EntityNotFoundException exception) {
        return ResponseEntity
                .status(NOT_FOUND)
                .body(new CustomError(NOT_FOUND, exception.getMessage()));
    }

    @ExceptionHandler({JsonPatchException.class, JsonProcessingException.class})
    public ResponseEntity<CustomError> handleBadRequestException(Exception exception) {
        return ResponseEntity
                .status(BAD_REQUEST)
                .body(new CustomError(BAD_REQUEST, exception.getMessage()));
    }
}
