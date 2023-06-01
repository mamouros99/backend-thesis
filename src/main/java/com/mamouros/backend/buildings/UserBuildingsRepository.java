package com.mamouros.backend.buildings;

import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Transactional
public interface UserBuildingsRepository extends JpaRepository<UserBuildings, Long> {


}
