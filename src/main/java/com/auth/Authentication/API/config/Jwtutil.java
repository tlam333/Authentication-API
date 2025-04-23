package com.auth.Authentication.API.config;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Component;

import java.nio.charset.StandardCharsets;
import java.security.Key;
import java.util.Date;


@Component
public class Jwtutil {
    //Needs to be a long secret with at least 32+ chars
    private final String SECRET = "skibiditungtungtungtungtungsahursecretkey123";
    private final long EXPIRATION = 1000 * 60 * 60; //Expires in an hr

    Key key = Keys.hmacShaKeyFor(SECRET.getBytes(StandardCharsets.UTF_8));
    public String generateToken(String username) {
        return Jwts.builder()
                .setSubject(username) //Who the token is for
                .setIssuedAt(new Date()) //When it was made
                .setExpiration(new Date(System.currentTimeMillis() + EXPIRATION))
                .signWith(key, SignatureAlgorithm.HS256)
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
