package com.mamouros.backend.buildings;

import jakarta.persistence.*;

@Entity
public class UserBuildings {

    @EmbeddedId
    private BuildingId id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private Boolean receiveEmails;



    public UserBuildings(){}
    public UserBuildings(BuildingId id, String buildingName){
        this.id = id;
        this.name = buildingName;
        this.receiveEmails = true;
    }

    public BuildingId getId() {
        return id;
    }

    public void setId(BuildingId id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Boolean getReceiveEmails() {
        return receiveEmails;
    }

    public void setReceiveEmails(Boolean receiveEmails) {
        this.receiveEmails = receiveEmails;
    }

    @Override
    public String toString() {
        return "UserBuildings{" +
                "buildingId='" + id.getId() + '\'' +
                "username='" + id.getUser().getUsername() + '\'' +
                ", buildingName='" + name + '\'' +
                ", receiveEmail='" + receiveEmails + '\'' +
                '}';
    }


}


