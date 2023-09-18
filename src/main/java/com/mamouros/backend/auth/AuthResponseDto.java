package com.mamouros.backend.auth;


import com.mamouros.backend.auth.User.User;

public class AuthResponseDto {
    private String token;
    private String username;
    private String name;
    private String role;
    private String email;

    public AuthResponseDto(String token, User user) {
        this.token = token;
        this.username = user.getUsername();
        this.name = user.getName();
        this.role = user.getRole().name();
        this.email = user.getEmail();
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
    public String getEmail() {
        return email;
    }

    public void setEmail(String email){
        this.email = email;
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
