package com.mamouros.backend.buildings;

import com.mamouros.backend.auth.User.User;
import jakarta.persistence.*;

@Entity
public class UserBuildings {

    @EmbeddedId
    private BuildingId id;

    @Column(nullable = false)
    private String name;



    public UserBuildings(){}
    public UserBuildings(BuildingId id, String buildingName){
        this.id = id;
        this.name = buildingName;
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

    @Override
    public String toString() {
        return "UserBuildings{" +
                "buildingId='" + id.getId() + '\'' +
                "username='" + id.getUser().getUsername() + '\'' +
                ", buildingName='" + name + '\'' +
                '}';
    }


}


