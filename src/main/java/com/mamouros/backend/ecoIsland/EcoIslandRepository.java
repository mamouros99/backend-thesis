package com.mamouros.backend.ecoIsland;


import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface EcoIslandRepository extends CrudRepository<EcoIsland, String> {

    @Query(
            value = "select eco_islands.* from user_buildings " +
                    "inner join eco_islands " +
                    "on user_buildings.name = eco_islands.building_name " +
                    "where user_buildings.username = :name",
            nativeQuery = true)
    Iterable<EcoIsland> findAllByUsername(@Param("name") String name);

    Iterable<EcoIsland> findAllByBuildingId(String buildingId);
}
