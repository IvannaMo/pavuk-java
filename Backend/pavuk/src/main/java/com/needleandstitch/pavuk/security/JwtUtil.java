package com.needleandstitch.pavuk.security;

import java.nio.charset.StandardCharsets;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;
import javax.crypto.SecretKey;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import com.needleandstitch.pavuk.model.User;
import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;


@Component
public class JwtUtil {
	@Value("${JWT_SECRET}")
    private String secret;

    @Value("${JWT_EXPIRATION_MS}")
    private long expirationMs;
	
	public String generateToken(User user) {
        Map<String, Object> claims = new HashMap<>();
        return createToken(claims, user.getEmail());
    }

    private String createToken(Map<String, Object> claims, String subject) {
        return Jwts.builder()
        		.claims(claims)
        		.subject(subject)
        		.issuedAt(new Date())
				.expiration(new Date(System.currentTimeMillis() + expirationMs))
				.signWith(Keys.hmacShaKeyFor(secret.getBytes(StandardCharsets.UTF_8)))
				.compact();
    }
	
	public String extractEmail(String token) {
        return extractClaim(token, Claims::getSubject);
    }
	
	public Date extractExpiration(String token) {
        return extractClaim(token, Claims::getExpiration);
    }
	
	public <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {
        final Claims claims = extractAllClaims(token);
        return claimsResolver.apply(claims);
    }
	
    private Claims extractAllClaims(String token) {
    	SecretKey decodedToken = Keys.hmacShaKeyFor(secret.getBytes(StandardCharsets.UTF_8));
    	
        return Jwts.parser()
				.verifyWith(decodedToken)
				.build()
		        .parseSignedClaims(token)
		        .getPayload();
    }
    
    private Boolean isTokenExpired(String token) {
        return extractExpiration(token).before(new Date());
    }

    public Boolean isTokenValid(String token) {
        try {
            extractAllClaims(token);
            return !isTokenExpired(token);
        } catch (JwtException | IllegalArgumentException e) {
            return false;
        }
    }
}
