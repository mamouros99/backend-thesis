package com.mamouros.backend.auth;


import com.mamouros.backend.BackendApplication;
import com.mamouros.backend.exceptions.ReportNotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Objects;


@Service
public class UserService {
    @Autowired
    UsersRepository usersRepository;
    private static final Logger log = LoggerFactory.getLogger(BackendApplication.class);

    public User findByUsername(String username){


        return usersRepository.findUserByUsername(username)
                .orElseThrow(() -> new RuntimeException("No user with username: " + username));
    }

}
