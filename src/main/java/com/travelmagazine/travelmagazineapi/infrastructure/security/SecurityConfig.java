package com.travelmagazine.travelmagazineapi.infrastructure.security;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {

    private final UserDetailsService userDetailsService;
    private final JwtAuthenticationFilter jwtAuthFilter;
    private final RestAuthenticationEntryPoint restAuthenticationEntryPoint;
    private final RestAccessDeniedHandler restAccessDeniedHandler;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .csrf(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests(auth -> auth
                        // 1. Δημόσια Endpoints - Πρόσβαση σε όλους χωρίς Token
                        .requestMatchers("/api/auth/**").permitAll()
                        .requestMatchers("/api/users/register").permitAll()
                        .requestMatchers("/uploads/**").permitAll()
                        .requestMatchers("/api/newsletter/**").permitAll()

                        // 2. Categories - GET για όλους, POST μόνο για ADMIN
                        .requestMatchers(HttpMethod.GET, "/api/categories/**").permitAll()
                        .requestMatchers(HttpMethod.POST, "/api/categories/**").hasRole("ADMIN")

                        // 3. Articles - GET για όλους, POST/PUT/DELETE μόνο για ADMIN
                        .requestMatchers(HttpMethod.GET, "/api/articles/**").permitAll()
                        .requestMatchers(HttpMethod.POST, "/api/articles/**").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.PUT, "/api/articles/**").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.DELETE, "/api/articles/**").hasRole("ADMIN")

                        // 4. Images - Απαιτείται απλά να είσαι συνδεδεμένος (Authenticated)
                        .requestMatchers(HttpMethod.POST, "/api/images/upload").hasRole("ADMIN")

                        // 5. Οτιδήποτε άλλο endpoint απαιτεί Authentication
                        .anyRequest().authenticated()
                )
                // Stateless session (δεν χρησιμοποιούμε JSESSIONID, μόνο JWT)
                .sessionManagement(session -> session
                        .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                )
                // Συνεπείς JSON απαντήσεις για 401 (μη αυθεντικοποιημένος) και 403 (χωρίς δικαίωμα)
                .exceptionHandling(exceptionHandling -> exceptionHandling
                        .authenticationEntryPoint(restAuthenticationEntryPoint)
                        .accessDeniedHandler(restAccessDeniedHandler)
                )
                .authenticationProvider(authenticationProvider())
                // Προσθήκη του JWT Filter ΠΡΙΝ το φίλτρο του Spring Security
                .addFilterBefore(jwtAuthFilter, UsernamePasswordAuthenticationFilter.class);

        return http.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public AuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
        authProvider.setUserDetailsService(userDetailsService);
        authProvider.setPasswordEncoder(passwordEncoder());
        return authProvider;
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration config) throws Exception {
        return config.getAuthenticationManager();
    }
}