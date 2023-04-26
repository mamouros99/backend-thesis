package com.mamouros.backend.auth;

import com.mamouros.backend.reports.Report;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UsersRepository extends CrudRepository<User, Integer> {
    @Query(value = "SELECT * FROM USERS u WHERE u.username=?1", nativeQuery = true)
    Optional<User> findUserByUsername(String username);
}
