package com.mamouros.backend.questions;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface QuestionRepository extends JpaRepository<Question, Long> {


    @Query(
            value = "select * from question " +
                    "where question.username = :name and question.show_question",
            nativeQuery = true)
    Iterable<Question> findAllByUsername(@Param("name") String name);
}
