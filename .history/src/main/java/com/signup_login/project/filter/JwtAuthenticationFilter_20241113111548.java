package com.signup_login.project.filter;

import java.io.IOException;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.signup_login.project.service.JWTService;
import com.signup_login.project.service.UserDetailsImp;

import io.micrometer.common.lang.NonNull;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    private final JWTService jwtService;

    private final UserDetailsImp userDetails;

    public JwtAuthenticationFilter(JWTService jwtService, UserDetailsImp userDetails) {
        this.jwtService = jwtService;
        this.userDetails = userDetails;
    }



    @Override
    protected void doFilterInternal(
        @NonNull HttpServletRequest request, 
        @NonNull HttpServletResponse response, 
        @NonNull FilterChain filterChain)
        throws ServletException, IOException {

            // String authHeader = request.getHeader( s: "Authorization");
            String authHeader = request.getHeader( s: "Authorization");
            if(authHeader == null || !authHeader.startsWith("Bearer") ){
                filterChain.doFilter(request,response);
                return;
             }

             String token = authHeader.substring( beginIndex: 7);
             String username = jwtService.extraactUsername(token);
             if(username != null && SecurityContextHolder.getContext().getAuthentication() == null){
                UsernamePasswordAuthenticationToken(
                    userDetails, null, userDetails.getAuthorities(

                    )



                )

             }
       
        
    } 
    
}
