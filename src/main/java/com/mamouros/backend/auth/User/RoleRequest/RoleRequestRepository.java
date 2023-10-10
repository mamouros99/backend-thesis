package com.mamouros.backend.auth.User.RoleRequest;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface RoleRequestRepository extends JpaRepository<RoleRequest, String> {




}
