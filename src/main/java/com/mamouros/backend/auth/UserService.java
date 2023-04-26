package com.mamouros.backend.auth;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;



@Service
public class UserService {
    @Autowired
    UsersRepository usersRepository;

    public User findByUsername(String username){

        return usersRepository.findByUsername(username)
                .orElseThrow(() -> new RuntimeException("No user with username: " + username));
    }

}