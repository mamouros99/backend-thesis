package com.mamouros.backend.auth;


import com.mamouros.backend.auth.User.User;

public class AuthResponseDto {
    private String token;
    private String username;
    private String name;
    private String role;

    public AuthResponseDto(String token, User user) {
        this.token = token;
        this.username = user.getUsername();
        this.name = user.getName();
        this.role = user.getRole().name();
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }
}
