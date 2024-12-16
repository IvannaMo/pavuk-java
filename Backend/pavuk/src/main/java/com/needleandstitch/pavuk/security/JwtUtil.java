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

/**
 * Utility class for handling JWT token operations, such as generating, parsing, and validating JWT tokens.
 * 
 * @author                     Needle & Stitch
 * @version                    1.0.0
 * @since                      15.12.2024
 */
@Component
public class JwtUtil {
     /**
     * The secret key used to sign and verify JWT tokens.
     */
	@Value("${JWT_SECRET}")
    private String secret;

     /**
     * Sets the expiration time in milliseconds for the JWT token.
     */
    @Value("${JWT_EXPIRATION_MS}")
    private long expirationMs;
	
    /**
     * Generates a JWT token for a given user.
     *
     * @param user             The user for whom the token is generated
     * @return                 The generated JWT token
     */
	public String generateToken(User user) {
        Map<String, Object> claims = new HashMap<>();
        return createToken(claims, user.getEmail());
    }

     /**
     * Creates a JWT token with the specified claims and subject.
     * The token is signed using the configured secret key and includes an expiration time.
     *
     * @param claims           The claims to include in the token
     * @param subject          The subject
     * @return                 The generated JWT token
     */
    private String createToken(Map<String, Object> claims, String subject) {
        return Jwts.builder()
        		.claims(claims)
        		.subject(subject)
        		.issuedAt(new Date())
				.expiration(new Date(System.currentTimeMillis() + expirationMs))
				.signWith(Keys.hmacShaKeyFor(secret.getBytes(StandardCharsets.UTF_8)))
				.compact();
    }
	
     /**
     * Extracts the email (subject) from the JWT token to identify the user associated with the token.
     *
     * @param token            The JWT token from which to extract the email
     * @return                 The email (subject) from the JWT token
     */
	public String extractEmail(String token) {
        return extractClaim(token, Claims::getSubject);
    }
	
    /**
     * Extracts the expiration date from the JWT token.
     *
     * @param token             The JWT token from which to extract the expiration date
     * @return                  The expiration date of the JWT token
     */
	public Date extractExpiration(String token) {
        return extractClaim(token, Claims::getExpiration);
    }

	 /**
     * Extracts a specific claim from the JWT token.
     *
     * @param token             The JWT token from which to extract the claim
     * @param claimsResolver    A function that extracts the desired claim from the Claims object
     * @param <T>               Claim type
     * @return                  The value of the extracted claim
     */
	public <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {
        final Claims claims = extractAllClaims(token);
        return claimsResolver.apply(claims);
    }

	 /**
     * Extracts all claims from the JWT token. This is used internally to retrieve all the claims from the token to perform further operations like validation or claim extraction.
     *
     * @param token             The JWT token from which to extract all claims
     * @return                  The Claims object containing all the claims from the token
     */
    private Claims extractAllClaims(String token) {
    	SecretKey decodedToken = Keys.hmacShaKeyFor(secret.getBytes(StandardCharsets.UTF_8));
    	
        return Jwts.parser()
				.verifyWith(decodedToken)
				.build()
		        .parseSignedClaims(token)
		        .getPayload();
    }

     /**
     * Checks if the JWT token has expired.
     *
     * @param token             The JWT token to check for expiration
     * @return                  True if the token is expired, false otherwise
     */
    private Boolean isTokenExpired(String token) {
        return extractExpiration(token).before(new Date());
    }

    /**
     * Validates the JWT token.
     *
     * @param token             The JWT token to validate
     * @return                  True if the token is valid, false otherwise
     */
    public Boolean isTokenValid(String token) {
        try {
            extractAllClaims(token);
            return !isTokenExpired(token);
        } catch (JwtException | IllegalArgumentException e) {
            return false;
        }
    }
}
