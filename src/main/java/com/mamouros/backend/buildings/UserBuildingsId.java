package com.mamouros.backend.buildings;

import java.io.Serializable;

public class UserBuildingsId implements Serializable {
    private String username;

    private String buildingId;

    // default constructor

    public UserBuildingsId(String accountNumber, String accountType) {
        this.username = accountNumber;
        this.buildingId = accountType;
    }

    // equals() and hashCode()
}