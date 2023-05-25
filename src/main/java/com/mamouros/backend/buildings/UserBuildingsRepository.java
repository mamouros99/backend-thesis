package com.mamouros.backend.buildings;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserBuildingsRepository extends JpaRepository<UserBuildings, String> {
}
