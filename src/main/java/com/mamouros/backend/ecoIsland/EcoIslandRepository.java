package com.mamouros.backend.ecoIsland;

import com.mamouros.backend.auth.User.User;
import com.mamouros.backend.buildings.UserBuildings;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.List;
import java.util.Objects;

@Repository
public interface EcoIslandRepository extends CrudRepository<EcoIsland, Long> {

    @Query(
            value = "select eco_islands.* from ((users inner join user_buildings  " +
                    "on users.username = user_buildings.user_username) " +
                    "inner join eco_islands " +
                    "on user_buildings.name = eco_islands.building_name)" +
                    "where users.username = :name",
            nativeQuery = true)
    Iterable<EcoIsland> findAllByUsername(@Param("name") String name);
}
