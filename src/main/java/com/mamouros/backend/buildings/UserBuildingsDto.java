package com.mamouros.backend.buildings;

public class UserBuildingsDto {

    private String id;
    private String name;

    public UserBuildingsDto(){}
    public UserBuildingsDto(String id, String name) {
        this.id = id;
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
