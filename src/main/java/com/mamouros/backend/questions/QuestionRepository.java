package com.mamouros.backend.questions;

import com.mamouros.backend.ecoIsland.EcoIsland;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

public interface QuestionRepository extends JpaRepository<Question, Long> {


    @Query(
            value = "select * from questions " +
                    "where questions.username = :name",
            nativeQuery = true)
    Iterable<Question> findAllByUsername(@Param("name") String name);
}
