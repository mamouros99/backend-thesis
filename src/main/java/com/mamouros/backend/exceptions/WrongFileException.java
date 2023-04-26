package com.mamouros.backend.exceptions;

public class WrongFileException extends RuntimeException{

    public WrongFileException(String type){
        super("File type should be csv but was " + type);
    }
}
