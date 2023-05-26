package com.mamouros.backend.buildings;

import jakarta.persistence.*;

@Entity
public class UserBuildings {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "username")
    private String username;

    @Column(name = "buildingId")
    private String buildingId;
    private String buildingName;

    public UserBuildings(){}
    public UserBuildings(String username, String buildingId, String buildingName){
        this.username = username;
        this.buildingId = buildingId;
        this.buildingName = buildingName;
    }

    public Long getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getBuildingId() {
        return buildingId;
    }

    public void setBuildingId(String buildingId) {
        this.buildingId = buildingId;
    }

    public String getBuildingName() {
        return buildingName;
    }

    public void setBuildingName(String buildingName) {
        this.buildingName = buildingName;
    }

    @Override
    public String toString() {
        return "UserBuildings{" +
                "username='" + username + '\'' +
                ", buildingId='" + buildingId + '\'' +
                ", buildingName='" + buildingName + '\'' +
                '}';
    }
}


