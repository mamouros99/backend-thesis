package com.mamouros.backend.exceptions;

public class ReportNotFoundException extends RuntimeException{

    public ReportNotFoundException(Long id){
        super("Could not find Report" + id);
    }
}
