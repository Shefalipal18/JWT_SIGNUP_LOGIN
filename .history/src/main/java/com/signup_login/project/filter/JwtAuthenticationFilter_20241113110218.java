package com.signup_login.project.filter;

import java.io.IOException;

import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.signup_login.project.service.JWTService;

import io.micrometer.common.lang.NonNull;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    private final JWTService jwtService;

    public JwtAuthenticationFilter(JWTService jwtService) {
        this.jwtService = jwtService;
    }


    @Override
    protected void doFilterInternal(
        @NonNull HttpServletRequest request, 
        @NonNull HttpServletResponse response, 
        @NonNull FilterChain filterChain)
        throws ServletException, IOException {

            // String authHeader = request.getHeader( s: "Authorization");
            String authHeader = request.getHeader( s: "Authori")
            if(authHeader == null || !authHeader.startsWith("Bearer") ){
                filterChain.doFilter(request,response);
                return;
             }

             String token = authHeader.substring( beginIndex: 7)
       
        
    } 
    
}
