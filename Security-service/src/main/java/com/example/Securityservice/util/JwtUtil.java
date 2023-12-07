package com.example.Securityservice.util;

import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.security.Key;
import java.util.Base64;
import java.util.Date;

public class JwtUtil {
    private static final String SECRET_KEY = "1320000312313200003123132000031231320000312313200003123132000031231320000312313200003123";
    private static final long EXPIRATION_TIME = 864000000; // 10 days

    public static String generateToken(String username) {
        Date now = new Date();
        Date expiryDate = new Date(now.getTime() + EXPIRATION_TIME);
        return Jwts.builder()
                .setSubject(username)
                .setIssuedAt(now)
                .setExpiration(expiryDate)
                .signWith( getSigningKey(),SignatureAlgorithm.HS512)
                .compact();
    }

    private static Key getSigningKey() {
        byte[] keyBytes = Base64.getDecoder().decode(SECRET_KEY);
        return Keys.hmacShaKeyFor(keyBytes);
    }

    private static final Logger logger = LoggerFactory.getLogger(JwtUtil.class);
    public static boolean validateToken(String token) {
        try {
            Jwts.parser().setSigningKey(encodeBase64(SECRET_KEY)).parseClaimsJws(token);
            return true;
        }catch (MalformedJwtException e) {
            logger.error("Invalid JWT token: {}", e.getMessage());
        } catch (ExpiredJwtException e) {
            logger.error("JWT token is expired: {}", e.getMessage());
        } catch (UnsupportedJwtException e) {
            logger.error("JWT token is unsupported: {}", e.getMessage());
        } catch (IllegalArgumentException e) {
            logger.error("JWT claims string is empty: {}", e.getMessage());
        } catch (Exception e) {
            logger.error(e.getMessage());
        }
        return false;
    }

    public static String getUsernameFromToken(String token) {
        Claims claims = Jwts.parser().setSigningKey(encodeBase64(SECRET_KEY)).parseClaimsJws(token).getBody();
        return claims.getSubject();
    }
    private static String encodeBase64(String value) {
        return value;
    }
}
