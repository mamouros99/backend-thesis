package com.mamouros.backend.config;


import com.mamouros.backend.auth.User;
import com.mamouros.backend.auth.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class JwtUserDetailsService implements UserDetailsService {

    @Autowired
    private UserService userService;

    @Override
    public UserDetails loadUserByUsername(String username) {
        try{
            User user = userService.findByUsername(username);
            return new MyUserPrincipal(user);
        }catch (RuntimeException e){
            throw new UsernameNotFoundException("Username not found with username: " + username);
        }
    }
}
