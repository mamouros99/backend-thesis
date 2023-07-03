package com.mamouros.backend.buildings;

import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
@Transactional
public interface UserBuildingsRepository extends JpaRepository<UserBuildings, BuildingId> {

    UserBuildings findUserBuildingsById(BuildingId id);

    @Query(
            value = "select users.email from users " +
                    "inner join user_buildings " +
                    "on user_buildings.username = users.username " +
                    "where user_buildings.name = :buildingName and (users.role = 'EDITOR' or users.role = 'ADMIN') and user_buildings.receive_emails"
                    ,
            nativeQuery = true)
    Iterable<String> findAllUsersByBuildingNameThatReceiveEmails(@Param("buildingName") String buildingName);

}
