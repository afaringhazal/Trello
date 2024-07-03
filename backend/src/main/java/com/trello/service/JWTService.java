package com.trello.service;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashMap;

@Service
public class JWTService {
    @Value("${security.jwt.secret-key}")
    private String secretKey;
    @Value("${security.jwt.expiration-time}")
    private Long expiration;

    private Claims parseClaim(String token) {
        byte[] signingKey = secretKey.getBytes();
        return Jwts
                .parserBuilder()
                .setSigningKey(Keys.hmacShaKeyFor(signingKey))
                .build()
                .parseClaimsJws(token)
                .getBody();
    }

    public String extractUsername(String token) {
        return parseClaim(token).getSubject();
    }

    public boolean isTokenValid(String token, UserDetails userDetails) {
        final Claims claims = parseClaim(token);
        return !claims.getExpiration().before(new Date()) &&
                claims.getSubject().equals(userDetails.getUsername());
    }

    public Date getExpirationTime(String token) {
        return parseClaim(token).getExpiration();
    }

    public String generateToken(UserDetails user) {
        return Jwts.builder()
                .setClaims(new HashMap<String, Object>())
                .setSubject(user.getUsername())
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + this.expiration))
                .signWith(Keys.hmacShaKeyFor(this.secretKey.getBytes()), SignatureAlgorithm.HS256)
                .compact();
    }
}
