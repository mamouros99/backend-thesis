package com.mamouros.backend.auth.User;

import com.mamouros.backend.buildings.UserBuildings;
import jakarta.persistence.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Entity
@Table(name = "USERS")
public class User implements UserDetails {

    private String name;
    @Id
    @Column(nullable = false, unique = true)
    private String username;

    @Column(nullable = false)
    private String email;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private Role role;

    @OneToMany(
            cascade = CascadeType.ALL,
            orphanRemoval = true
    )
    @JoinColumn(name = "user_username")
    private List<UserBuildings> buildings = new ArrayList<>();

    public User() {
    }

    public User(String name, String username, String email, Role role) {
        this.name = name;
        this.username = username;
        this.email = email;
        this.role = role;
    }

    public User(UserDto userDto) {
        this.username = userDto.getUsername();
        this.name = userDto.getName();
        this.email = userDto.getEmail();
        this.role = Role.valueOf(userDto.getRole());
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
        return List.of(new SimpleGrantedAuthority(role.name()));
    }

    @Override
    public String getPassword() {
        return null;
    }

    public List<UserBuildings> getBuildings() {
        return buildings;
    }

    public void setBuildings(List<UserBuildings> buildings) {
        this.buildings = buildings;
    }

    @Override
    public String toString() {
        return "User{" +
                "name='" + name + '\'' +
                ", username='" + username + '\'' +
                ", email='" + email + '\'' +
                ", role=" + role +
                ", buildings=" + buildings +
                '}';
    }
}
