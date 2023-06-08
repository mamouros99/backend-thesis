package com.mamouros.backend.auth;

import com.mamouros.backend.auth.User.User;
import com.mamouros.backend.auth.User.UserService;
import com.mamouros.backend.config.JwtService;
import com.mamouros.backend.exceptions.UserNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AuthService {

    @Autowired
    private UserService userService;

    @Autowired
    private JwtService jwtService;

    public AuthResponseDto fenixAuth(FenixEdu fenix) {
        String username = fenix.getUsername();
        User user;
        try {
            user = userService.findByUsername(username);
        }catch (UserNotFoundException e){
            user = userService.saveUserFromFenix(fenix);
        }

        //user = userService.findByUsername("ist123452");

        String token = jwtService.generateToken(user);

        return new AuthResponseDto(token, user);
    }


}
