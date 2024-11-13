package com.signup_login.project.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;

import com.signup_login.project.filter.JwtAuthenticationFilter;
import com.signup_login.project.service.UserDetailsServiceImp;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    private final UserDetailsServiceImp userDetailsServiceImp;

    private final JwtAuthenticationFilter jwtAuthenticationFilter;

    

public SecurityConfig(UserDetailsServiceImp userDetailsServiceImp, JwtAuthenticationFilter jwtAuthenticationFilter) {
        this.userDetailsServiceImp = userDetailsServiceImp;
        this.jwtAuthenticationFilter = jwtAuthenticationFilter;
    }



@Bean
public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception{
    return http
            .csrf(AbstractHttpConfigurer::disable)
            .authorizeHttpRequests(
                req->req.requestMatchers("/login/**","/register/**")
                        .permitAll()
                        .anyRequest()
                        .authentication()

            ).userDetailsService(userDetailsServiceImp)
            .sessionManagement(session->session
            .sessionCreationPolicy(SessionCreationPolicy.STATELESS))
}
    
}
