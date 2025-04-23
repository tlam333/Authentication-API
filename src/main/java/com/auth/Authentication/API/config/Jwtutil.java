package com.auth.Authentication.API.config;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.stereotype.Component;

import java.util.Date;


@Component
public class Jwtutil {
    private final String SECRET = "supersecretkey123";
    private final long EXPIRATION = 1000 * 60 * 60; //Expires in an hr

    public String generateToken(String username) {
        return Jwts.builder()
                .setSubject(username) //Who the token is for
                .setIssuedAt(new Date()) //When it was made
                .setExpiration(new Date(System.currentTimeMillis() + EXPIRATION))
                .signWith(SignatureAlgorithm.HS256, SECRET)
                .compact(); // Turn it into a token string
    }

    public String extractUsername(String token){
        return Jwts.parser().setSigningKey(SECRET)
                .parseClaimsJws(token)
                .getBody()
                .getSubject(); //Read username
    }

    public boolean validate(String token){
        try {
            Jwts.parser().setSigningKey(SECRET).parseClaimsJws(token); //Try decoding it
            return true;
        }  catch (Exception e){
            return false; //Token is expired or invalid
        }

    }

}
