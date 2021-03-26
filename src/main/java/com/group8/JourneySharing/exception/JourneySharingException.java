package com.group8.JourneySharing.exception;

import org.springframework.validation.Errors;

public class JourneySharingException extends RuntimeException {
    private static final long serialVersionUID = 1L;
    private Errors errors;

    public JourneySharingException() {
        super();
    }

    public JourneySharingException(String message) {
        super( message);
    }

    public JourneySharingException(String message, Throwable cause) {
        super(message, cause);
    }

    public JourneySharingException(String message, Errors errors) {
        super(message);
        this.errors = errors;
    }

    public Errors getErrors() {
        return errors;
    }
}
