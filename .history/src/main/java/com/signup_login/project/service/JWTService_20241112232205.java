package com.signup_login.project.service;

import java.util.Date;
import java.util.function.Function;

import javax.crypto.SecretKey;

import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;

@Service
public class JWTService {
    private final String SECRET_KEY = " bc29f718c772ed74ff1bdd4f9f4fc60230a52bba7cb5bbab518c9bc449901a2c";


    public String extraactUsername(String token) {
        return extractAlClaims(token, Claims::getSubject);
    }


    public boolean isValid(String token, UserDetails user) {
        String username = extraactUsername(token);
        return (username.equals(user.getUsername())) && !isTokenExpired(token);
    }


    private boolean isTokenExpired(String token) {
        return extractExpiration(token).before(new Date());
    }

    private Date extractExpiration(String token){
        return extractAlClaims(token, Claims::getExpiration);
    }

    public <T> T extractAlClaims(String token, Function<Claims, T> resolver) {
        Claims claims = extractAlClaims(token);
        return resolver.apply(claims);
    }

    private Claims extractAlClaims(String token) {

        return Jwts
        .parser()
        .verifyWith(getSigninKey())
        .build()
        .parseSignedClaims(token)
        .getPayload();
        
    }

    public String generateToken(User user) {
        String token = Jwts
        .builder()
        .subject(user.getUsername())
        .issuedAt(new Date(System.currentTimeMillis()))
        .expiration(new Date(System.currentTimeMillis() + 24*60*60*1000))
        .signWith(getSigninKey())
        .compact();

        return token;
        
    }

    private SecretKey getSigninKey() {
        byte[] Keybytes = Decoders.BASE64URL.decode(SECRET_KEY);
        return Keys.hmacShaKeyFor(Keybytes);
    }

}
