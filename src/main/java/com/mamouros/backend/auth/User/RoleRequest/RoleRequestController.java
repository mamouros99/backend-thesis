package com.mamouros.backend.auth.User.RoleRequest;

import com.mamouros.backend.auth.User.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping(path = "/roleRequest")
public class RoleRequestController {

    @Autowired
    private RoleRequestService roleRequestService;
    @PostMapping(path="/add/{username}")
    public @ResponseBody void addRoleRequest(@PathVariable String username){
         roleRequestService.addRoleRequest(username);
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @DeleteMapping(path="/remove/{username}")
    public @ResponseBody void deleteRoleRequest(@PathVariable String username){
        roleRequestService.deleteRoleRequest(username);
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @GetMapping(path="/get/all")
    public @ResponseBody Iterable<User> getAllRoleRequest(){
        return roleRequestService.getAll();
    }




}
