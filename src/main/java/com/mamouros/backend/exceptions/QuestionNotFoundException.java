package com.mamouros.backend.exceptions;

public class QuestionNotFoundException extends RuntimeException{

    public QuestionNotFoundException(Long id){
        super("Could not find Question with id - " + id);
    }
}
