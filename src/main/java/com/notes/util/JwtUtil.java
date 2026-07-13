package com.notes.util;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.util.Date;

@Component
public class JwtUtil {

    private String secret;
    private Long expiration;
    private Long refreshExpiration;


    private SecretKey getKey(){
        return Keys.hmacShaKeyFor(Decoders.BASE64.decode(secret));
    }


    public String generateAccessToken(String username){
        return Jwts.builder()
                .subject(username)
                .issuedAt(new Date())
                .expiration(new Date(System.currentTimeMillis() + expiration))
                .claim("type","access")
                .signWith(getKey())
                .compact();
    }

    public String generateRefreshToken(String username){
        return Jwts.builder()
                .subject(username)
                .issuedAt(new Date())
                .expiration(new Date(System.currentTimeMillis() + refreshExpiration))
                .claim("type","refresh")
                .signWith(getKey())
                .compact();
    }

    public String extractUsername(String token){
        return extractClaims(token)
                .getSubject();
    }

    public boolean validateToken(String token){
        return !isTokenExpired(token);
    }


    public boolean isTokenExpired(String token){
        return extractClaims(token).getExpiration().before(new Date());
    }

    private Claims extractClaims(String token) {
        return Jwts.parser()
                .verifyWith(getKey())
                .build()
                .parseSignedClaims(token)
                .getPayload();
    }

}
