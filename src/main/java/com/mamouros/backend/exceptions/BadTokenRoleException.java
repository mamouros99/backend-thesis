package com.mamouros.backend.exceptions;

public class BadTokenRoleException extends BadTokenException {
    public BadTokenRoleException() {
        super("Roles did not match");
    }
}
