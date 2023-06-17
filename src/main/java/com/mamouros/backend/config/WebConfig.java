package com.mamouros.backend.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
@EnableWebMvc
public class WebConfig implements WebMvcConfigurer {

    @Value("${my.ip:Unknown}")
    private String ip;

    @Override
    public void addCorsMappings(CorsRegistry registry) {

        final String[] allowedOrigins = { ip + ":8081",  ip + ":8080"};

        registry.addMapping("/ecoisland/**")
                .allowedOrigins(allowedOrigins)
                .allowedMethods("GET","POST","PUT","DELETE");
        registry.addMapping("/report/**")
                .allowedOrigins(allowedOrigins)
                .allowedMethods("GET","POST","DELETE");
        registry.addMapping("/auth/fenix/**")
                .allowedOrigins(allowedOrigins[0])
                .allowedMethods("GET");
        registry.addMapping("/user/**")
                .allowedOrigins(allowedOrigins[0])
                .allowedMethods("GET","POST","PUT","DELETE");
        registry.addMapping("/building/**")
                .allowedOrigins(allowedOrigins[0])
                .allowedMethods("GET", "POST", "DELETE");
    }
}