package com.mamouros.backend.buildings;

public class UserBuildingsDto {

    private String id;
    private String name;
    private String username;

    public UserBuildingsDto(){}
    public UserBuildingsDto(String id, String name, String username) {
        this.id = id;
        this.name = name;
        this.username = username;

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
