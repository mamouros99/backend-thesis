package com.mamouros.backend.exceptions;

public class IslandNotFoundException extends RuntimeException{

    public IslandNotFoundException(Long id){
        super("Could not find EcoIsland " + id);
    }
}
