package com.mamouros.backend.exceptions;

public class ReportNotFoundException extends RuntimeException{

    public ReportNotFoundException(Integer id){
        super("Could not find Report" + id);
    }
}
