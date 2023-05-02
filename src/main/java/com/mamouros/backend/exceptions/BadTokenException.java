package com.mamouros.backend.exceptions;

public class BadTokenException extends RuntimeException {
    public BadTokenException(String message) {
        super("Invalid Token - " + message);
    }
}
