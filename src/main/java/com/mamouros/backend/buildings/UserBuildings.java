package com.mamouros.backend.buildings;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.IdClass;

@Entity
@IdClass(UserBuildingsId.class)
public class UserBuildings {

    @Id
    private String username;

    @Id
    private String buildingId;
    private String buildingName;

    public UserBuildings(){}
    public UserBuildings(String username, String buildingId){
        this.username = username;
        this.buildingId = buildingId;
    }


}


