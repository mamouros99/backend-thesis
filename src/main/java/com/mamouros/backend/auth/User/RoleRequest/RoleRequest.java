package com.mamouros.backend.auth.User.RoleRequest;

import jakarta.persistence.*;

@Entity
public class RoleRequest {

    @Id
    private String id;

    public RoleRequest(String username) {
        this.id = username;
    }

    public RoleRequest() {

    }

    public void setId(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }
}
