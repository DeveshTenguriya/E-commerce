package com.example.E_commerce.Config;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.nio.charset.StandardCharsets;
import java.util.Date;

@Slf4j
@Service
public class JwtServices {

    @Value("${app.jwt.secret}")
    private String jwtSecret;

    @Value("${app.jwt.expiration}")
    private Long Expiration;

 public String generateToken(UserDetails userDetails) {
     log.info("Generating JWT token for user={}", userDetails.getUsername());

     String token = Jwts.builder()
             .setSubject(userDetails.getUsername())
             .setIssuedAt(new Date())
             .setExpiration(new Date(System.currentTimeMillis()+ Expiration))
             .signWith(Keys.hmacShaKeyFor(jwtSecret.getBytes(StandardCharsets.UTF_8)), SignatureAlgorithm.HS256)
             .compact();

     log.debug("JWT token generated successfully for user={}",
             userDetails.getUsername());

     return token;
 }


 public String extractUsername(String token) {
     return Jwts.parserBuilder()
             .setSigningKey(Keys.hmacShaKeyFor(jwtSecret.getBytes(StandardCharsets.UTF_8)))
             .build()
             .parseClaimsJws(token)
             .getBody()
             .getSubject();

 }

 public boolean validateToken(String token, UserDetails userDetails){
     String username= extractUsername(token);

    return username.equals(userDetails.getUsername());
 }

}