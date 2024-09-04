package com.Coding.SecurityApplication.SecurityApplication.service;

import com.Coding.SecurityApplication.SecurityApplication.entities.User;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import java.nio.charset.StandardCharsets;
import java.util.Date;
import java.util.Set;

@Service
public class JwtService {


    @Value("${jwt.secretKey}")
    private String jwtSecretKey;

    private SecretKey getSecretKey() {
        return Keys.hmacShaKeyFor(jwtSecretKey.getBytes(StandardCharsets.UTF_8));
    }
    public String generateAccessToken(User user) {

        return Jwts.builder().subject(user.getId().toString())
                .claim("email",user.getUsername())
                .claim("roles", user.getAuthorities())
                .issuedAt(new Date())
                .expiration(new Date(System.currentTimeMillis() + 1000 * 60))
                .signWith(getSecretKey())
                .compact();
    }

    public String generateRefereshToken(User user) {

        return Jwts.builder().subject(user.getId().toString())
                .claim("email",user.getUsername())
                .claim("roles", user.getRoles())
                .issuedAt(new Date())
                .expiration(new Date(System.currentTimeMillis() + 1000 * 60 * 60 * 24))
                .signWith(getSecretKey())
                .compact();
    }

    public Long generateIdFromToken(String token) {

        Claims claim = Jwts.parser().verifyWith(getSecretKey()).build().parseSignedClaims(token).getPayload();

        return Long.valueOf(claim.getSubject());
    }

}
