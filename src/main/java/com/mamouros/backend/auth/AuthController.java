package com.mamouros.backend.auth;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping(path = "/auth")
public class AuthController {
    @Autowired
    private UserService userService;


    @GetMapping("/fenix/{code}")
    public @ResponseBody User fenixAuth(@PathVariable String code) {
        FenixEdu fenix = new FenixEdu();
        fenix.setPerson(code);
        //check if on db

        return userService.findByUsername(fenix.getUsername());
    }

}
