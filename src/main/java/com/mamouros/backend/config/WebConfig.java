package com.mamouros.backend.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
@EnableWebMvc
public class WebConfig implements WebMvcConfigurer {

    private final String[] allowedOrigins = {"http://localhost:8081",  "http://localhost:8080"};
    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/ecoisland/**")
                .allowedOrigins(allowedOrigins)
                .allowedMethods("GET","POST","DELETE");
        registry.addMapping("/report/**")
                .allowedOrigins(allowedOrigins)
                .allowedMethods("GET","POST","DELETE");
        registry.addMapping("/auth/fenix/**")
                .allowedOrigins(allowedOrigins[0])
                .allowedMethods("GET");
        registry.addMapping("/user/**")
                .allowedOrigins(allowedOrigins[0])
                .allowedMethods("GET","POST","PUT","DELETE");
    }
}