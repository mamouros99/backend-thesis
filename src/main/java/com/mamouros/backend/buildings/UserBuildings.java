package com.mamouros.backend.buildings;

import jakarta.persistence.*;

@Entity
public class UserBuildings {

    @Id
    @Column(name = "buildingId")
    private String buildingId;
    private String name;

    public UserBuildings(){}
    public UserBuildings( String buildingId, String buildingName){
        this.buildingId = buildingId;
        this.name = buildingName;
    }

    public String getBuildingId() {
        return buildingId;
    }

    public void setBuildingId(String buildingId) {
        this.buildingId = buildingId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "UserBuildings{" +
                "buildingId='" + buildingId + '\'' +
                ", buildingName='" + name + '\'' +
                '}';
    }
}


