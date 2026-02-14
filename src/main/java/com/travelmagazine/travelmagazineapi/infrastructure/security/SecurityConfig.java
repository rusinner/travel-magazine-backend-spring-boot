package com.travelmagazine.travelmagazineapi.infrastructure.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .csrf(AbstractHttpConfigurer::disable) // Απενεργοποίηση CSRF για να δέχεται POST από Postman
                .authorizeHttpRequests(auth -> auth
                        .anyRequest().permitAll() // Επιτρέπει όλα τα αιτήματα χωρίς login
                );

        return http.build();
    }
}