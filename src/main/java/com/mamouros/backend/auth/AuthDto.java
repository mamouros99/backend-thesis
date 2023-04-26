package com.mamouros.backend.auth;

import java.io.Serializable;

public class AuthDto implements Serializable {

    private String username;
    private String name;
    private String email;

    private String token;
}
