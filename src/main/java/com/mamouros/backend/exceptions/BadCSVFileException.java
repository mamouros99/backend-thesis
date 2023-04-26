package com.mamouros.backend.exceptions;

public class BadCSVFileException extends RuntimeException{

    public BadCSVFileException(String msg){
        super("Ficheiro .csv tem de conter  estas colunas: building, floor, description, bins");
    }
}
