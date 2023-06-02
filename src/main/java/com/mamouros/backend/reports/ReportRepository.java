package com.mamouros.backend.reports;

import com.mamouros.backend.ecoIsland.EcoIsland;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface ReportRepository extends CrudRepository<Report, Long> {

    @Query(
            value = "select eco_islands.* from ((users inner join user_buildings  " +
                    "on users.username = user_buildings.user_username) " +
                    "inner join eco_islands " +
                    "on user_buildings.name = eco_islands.building_name)" +
                    "where users.username = :name",
            nativeQuery = true)
    Iterable<Report> findAllByUsername(@Param("name") String name);


}
