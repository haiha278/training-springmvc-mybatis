package com.example.training.security;

import io.jsonwebtoken.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
@Slf4j
public class JwtTokenProvider {
    @Value("${jwt.secret-key}")
    private String SECRET_KEY;
    @Value("${jwt.expiration-time}")
    private long EXPIRATION;

    public String generateToken(CustomUserDetails customUserDetails) {
        Date now = new Date();
        Date expirationDate = new Date(now.getTime() + EXPIRATION);
        return Jwts.builder()
                .setSubject(customUserDetails.getUsername())
                .setIssuedAt(now)
                .setExpiration(expirationDate)
                .signWith(SignatureAlgorithm.HS256, SECRET_KEY)
                .compact();
    }

    public String getUsernameFromToken(String token) {
        Claims claims = Jwts.parserBuilder()
                .setSigningKey(SECRET_KEY)
                .build()
                .parseClaimsJws(token)
                .getBody();
        return claims.getSubject();
    }

    public boolean validateToken(String token) {
        try {
            Jwts.parser().setSigningKey(SECRET_KEY)
                    .parseClaimsJws(token);
            return true;
        } catch (MalformedJwtException ex) {
            log.error("invalid jwt token!");
        } catch (ExpiredJwtException ex) {
            log.error("expired jwt token");
        } catch (UnsupportedJwtException ex) {
            log.error("unsupported jwt token. Cannot parse");
        } catch (IllegalArgumentException ex) {
            log.error("jwt claims string is empty");
        }
        return false;
    }
}
