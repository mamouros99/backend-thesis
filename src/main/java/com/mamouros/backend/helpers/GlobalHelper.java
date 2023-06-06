package com.mamouros.backend.helpers;


import com.mamouros.backend.auth.User.User;
import org.springframework.security.core.context.SecurityContextHolder;

public class GlobalHelper {

    public static User getUserFromSecurityContext(){
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return (User) principal;
    }

}
