package com.mamouros.backend.auth.User;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UsersRepository extends JpaRepository<User, String> {

    Optional<User> findByUsername(String username);

    Iterable<User> findAllByReceiveQuestionsTrue();

    @Query(
            value = "select users.* from users " +
                    "inner join role_request " +
                    "on users.username = role_request.id" ,
            nativeQuery = true)
    Iterable<User> findAllUsersWithRequests();

}
