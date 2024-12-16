package com.needleandstitch.pavuk.security;

import java.io.IOException;
import java.util.ArrayList;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import com.needleandstitch.pavuk.model.User;
import com.needleandstitch.pavuk.repository.UserRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/**
 * Filter for JWT token validation.
 * 
 * @author                     Needle & Stitch
 * @version                    1.0.0
 * @since                      15.12.2024
 */
@Component
public class JwtFilter extends OncePerRequestFilter {
    /**
     * Utility class for handling JWT operations.
     * <p>
     * This component provides methods for validating and extracting information from JWT tokens.
     * </p>
     */
	private final JwtUtil jwtUtil;
    /**
     * Repository for interacting with the User entity.
     * <p>
     * This repository is used to fetch user information based on given JWT token.
     * </p>
     */
	private final UserRepository userRepository;
	
    /**
     * Filter constructor.
     * 
     * @param jwtUtil          The utility class for JWT operations
     * @param userRepository   The repository for fetching user details
     */
	public JwtFilter(JwtUtil jwtUtil, UserRepository userRepository) {
        this.jwtUtil = jwtUtil;
        this.userRepository = userRepository;
    }
	 /**
     * Filters the request to check for a valid JWT token.
     * <p>
     * This method retrieves the JWT token from the request cookies, validates it, and if valid, extracts the user's email from the token. 
     * It then loads the user from the database and sets the authentication context for the request.
     * </p>
     * 
     * @param request           The HTTP request
     * @param response          The HTTP response
     * @param filterChain       The filter chain to pass the request to the next filter
     * 
     * @throws ServletException if an error occurs during filter processing
     * @throws IOException if an I/O error occurs during filter processing
     */
	@Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String token = null;

        if (request.getCookies() != null) {
            for (Cookie cookie : request.getCookies()) {
                if ("jwt".equals(cookie.getName())) {
                    token = cookie.getValue();
                    break;
                }
            }
        }

        if (token != null && jwtUtil.isTokenValid(token)) {
            String email = jwtUtil.extractEmail(token);
            User user = userRepository.findByEmail(email)
            .orElseThrow(() -> new EntityNotFoundException("User not found with email: " + email));
            
            SecurityContextHolder.getContext().setAuthentication(
                    new org.springframework.security.authentication.UsernamePasswordAuthenticationToken(user, null, new ArrayList<>())
            );
        }

        filterChain.doFilter(request, response);
    }
}
