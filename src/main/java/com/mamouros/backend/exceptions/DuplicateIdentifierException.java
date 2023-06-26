package com.mamouros.backend.exceptions;

public class DuplicateIdentifierException extends RuntimeException{

    public DuplicateIdentifierException(String id){
        super("Identifier " + id + " was already in use");
    }
}
