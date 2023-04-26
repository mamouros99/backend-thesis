package com.mamouros.backend.ecoIsland;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EcoIslandRepository extends CrudRepository<EcoIsland, Integer> {
}
