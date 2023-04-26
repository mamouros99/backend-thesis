package com.mamouros.backend.auth;

import com.mamouros.backend.config.JwtTokenUtil;
import com.mamouros.backend.config.JwtUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping(path = "/auth")
public class AuthController {
    @Autowired
    private UserService userService;

    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    @Autowired
    private JwtUserDetailsService userDetailsService;


   @CrossOrigin
   @GetMapping("/fenix/{code}")
    public @ResponseBody String fenixAuth(@PathVariable String code) {
        FenixEdu fenix = new FenixEdu();
        fenix.setPerson(code);
        //check if on db
        String username = fenix.getUsername();
        UserDetails userDetails = userDetailsService
                .loadUserByUsername(username);

        return jwtTokenUtil.generateAccessToken(userDetails);
    }

}
