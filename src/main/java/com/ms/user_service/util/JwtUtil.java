package com.ms.user_service.util;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.security.Key;
import java.util.Date;

@Component
public class JwtUtil {
    @Value("${jwt.secret}")
    private String secret;

    @Value("${jwt.access-token-expiration}")
    private long accessExpiration;

    @Value("${jwt.refresh-token-expiration}")
    private long refreshExpiration;

    private SecretKey getKey(){
        return Keys.hmacShaKeyFor(secret.getBytes());
    }
    public String generateAccessToken(String username){
        return Jwts.builder()
                .subject(username)
                .issuedAt(new Date())
                .expiration(new Date(System.currentTimeMillis() + accessExpiration))
                .signWith(this.getKey())
                .compact();
    }

    public String getRefreshToken(){
        return "refresh token";
    }

    private Claims extractClaims(String token){
        return Jwts.parser()
                .verifyWith(this.getKey())
                .build()
                .parseSignedClaims(token)
                .getPayload();
    }

    public String extractUsername(String token){
        return this.extractClaims(token).getSubject();
    }

    public boolean isTokenExipred(String token){
        return this.extractClaims(token).getExpiration().before(new Date());
    }

    public boolean validateToken(String username, UserDetails userDetails,String token){
        return username.equals(userDetails.getUsername()) && !this.isTokenExipred((token));
    }
}
