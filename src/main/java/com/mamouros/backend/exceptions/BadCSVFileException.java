package com.mamouros.backend.exceptions;

public class BadCSVFileException extends RuntimeException{

    public BadCSVFileException(String msg){
        super("Ficheiro .csv tem de conter  estas colunas: \"id\", \"building\", \"buildingId\", \"floor\", \"description\", \"bins\", \"xPos\", \"yPos\", \"identifier\"");
    }
}
