package com.mamouros.backend.buildings;

import com.mamouros.backend.auth.User.User;
import com.mamouros.backend.ecoIsland.EcoIsland;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserBuildingsRepository extends JpaRepository<UserBuildings, String> {



}
