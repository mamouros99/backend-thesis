package com.mamouros.backend.auth.User.RoleRequest;

import com.mamouros.backend.auth.User.User;
import com.mamouros.backend.auth.User.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class RoleRequestService {

    @Autowired
    private RoleRequestRepository roleRequestRepository;

    @Autowired
    private UserService userService;
    public void addRoleRequest(String username) {

        roleRequestRepository.save(new RoleRequest(username));
    }

    public void deleteRoleRequest(String username) {

        roleRequestRepository.deleteById(username);

    }

    public Iterable<User> getAll() {
        return userService.findAllUsersWithRequests();
    }
}
