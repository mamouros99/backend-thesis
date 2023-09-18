package com.mamouros.backend.auth.User;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping(path = "/user")
public class UserController {

    @Autowired
    private UserService userService;

   @PreAuthorize("hasRole('ROLE_ADMIN')")
   @DeleteMapping("/delete/{username}")
   public @ResponseBody void deleteEcoIslandByUsername(@PathVariable String username){
       userService.deleteById(username);
   }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @PutMapping("/update/{username}/{role}")
    public @ResponseBody void updateRoleByUsername(@PathVariable String username, @PathVariable Role role ){
       userService.updateUser( username, role);
    }

    @PreAuthorize("hasAnyRole('ROLE_VIEWER','ROLE_EDITOR', 'ROLE_ADMIN')")
    @PutMapping("/receiveQuestion/{username}")
    public @ResponseBody void toggleReceiveQuestionById(@PathVariable String username ){
       userService.toggleReceiveQuestionById( username);
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @GetMapping("/get/{username}")
    public @ResponseBody User getUserByID(@PathVariable String username ){
        return userService.findByUsername(username);
    }

    @PreAuthorize("hasAnyRole('ROLE_VIEWER','ROLE_EDITOR', 'ROLE_ADMIN')")
    @GetMapping("/findMyUser")
    public @ResponseBody User findMyUser( ){
        return userService.findMyUser();
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @GetMapping("/get/all")
    public @ResponseBody Iterable<User> getAllUsers(){
        return userService.findAllUsers();
    }


}
