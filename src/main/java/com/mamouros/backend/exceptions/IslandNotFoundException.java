package com.mamouros.backend.exceptions;

public class IslandNotFoundException extends RuntimeException{

    public IslandNotFoundException(String id){
        super("Could not find EcoIsland " + id);
    }
}
