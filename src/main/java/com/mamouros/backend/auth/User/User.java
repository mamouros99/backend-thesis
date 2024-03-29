package com.mamouros.backend.auth.User;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.mamouros.backend.buildings.UserBuildings;
import jakarta.persistence.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.*;

@Entity
@Table(name = "USERS")
public class User implements UserDetails {

    private String name;

    @Id
    @Column(nullable = false)
    private String username;

    @Column(nullable = false)
    private String email;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private Role role;

    @Column(nullable = false)
    private Boolean receiveQuestions;

    @OneToMany(
            cascade = CascadeType.ALL,
            targetEntity = UserBuildings.class
    )
    @JsonBackReference
    private Set<UserBuildings> buildings;

    public User() {
    }

    public User(String name, String username, String email, Role role) {
        this.name = name;
        this.username = username;
        this.email = email;
        this.role = role;
        this.receiveQuestions = false;
    }


    public String getName() {
        return name;
    }


    public String getUsername() {
        return username;
    }

    public void setName(String name) {
        this.name = name;
    }


    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority("ROLE_" + role.name()));
    }

    @Override
    public String getPassword() {
        return null;
    }

    public Set<UserBuildings> getBuildings() {
        return buildings;
    }

    public void setBuildings(Set<UserBuildings> buildings) {
        this.buildings = buildings;
    }

    public Boolean getReceiveQuestions() {
        return receiveQuestions;
    }

    public void setReceiveQuestions(Boolean receiveQuestions) {
        this.receiveQuestions = receiveQuestions;
    }

    @Override
    public String toString() {
        return "User{" +
                "name='" + name + '\'' +
                ", username='" + username + '\'' +
                ", email='" + email + '\'' +
                ", role=" + role +
                ", buildings=" + buildings +
                ", receiveQuestions=" + receiveQuestions +
                '}';
    }
}
