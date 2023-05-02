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
    @PutMapping("/update")
    public @ResponseBody void updateRoleByUsername(@RequestBody UserDto userDto ){
       User user = new User(userDto);
       userService.updateUser( user);
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @GetMapping("/get/{username}")
    public @ResponseBody void getUserByID(@PathVariable String username ){
        userService.findByUsername(username);
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @GetMapping("/get/all")
    public @ResponseBody Iterable<User> getAllUsers(){
        return userService.findAllUsers();
    }
}
