package edu.umss.fcyt.posgrado.identity.errors.exceptions;

public class EntityNotFoundException extends RuntimeException {

    private static final String NOT_FOUND_MESSAGE_FORMAT = "%s not found.";

    public EntityNotFoundException(String classType) {
        super(String.format(NOT_FOUND_MESSAGE_FORMAT, classType));
    }
}
