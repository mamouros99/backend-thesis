package com.mamouros.backend.exceptions;

public class IslandNotFoundException extends RuntimeException{

    public IslandNotFoundException(Integer id){
        super("Could not find EcoIsland " + id);
    }
}
