package com.needleandstitch.pavuk.config;

import java.util.List;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import com.needleandstitch.pavuk.security.JwtFilter;

/**
 * Configuration class for setting up application security.
 *
 * <p>It configures CORS, CSRF, authentication rules, and integrates JWT filtering for request authorization. Specific endpoints are allowed to bypass authentication for public access.</p>
 *
 * @author                          Needle &amp; Stitch
 * @version                         1.0.0
 * @since                           15.12.2024
 */
@Configuration
@EnableWebSecurity
public class SecurityConfig {
     /** 
      * The custom JWT filter used to validate and authenticate incoming requests. 
      */
	private final JwtFilter jwtFilter;

    /**
     * Constructor for SecurityConfig that injects the required JwtFilter.
     *
     * @param jwtFilter             The filter responsible for processing and validating JWT tokens
     */
    public SecurityConfig(JwtFilter jwtFilter) {
        this.jwtFilter = jwtFilter;
    }
	
     /**
     * Configures Cross-Origin Resource Sharing (CORS) settings for the application.
     *
     * <p>Defines the allowed origins, HTTP methods, and headers that can be 
     * used by external clients when interacting with the application.</p>
     *
     * @return                      The configured CorsConfigurationSource instance
     */
	@Bean
    CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration configuration = new CorsConfiguration();
        configuration.addAllowedOriginPattern("https://192.168.*.*:*");
        configuration.setAllowedOrigins(List.of("http://localhost:5173", "http://127.0.0.1:5173"));
        configuration.setAllowedMethods(List.of("GET", "POST", "PUT", "DELETE"));
        configuration.setAllowedHeaders(List.of("*"));
        configuration.setAllowCredentials(true);
        
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration);
        return source;
    }
	
    /**
     * Configures the main security filter chain for the application.
     *
     * <p>Sets up CORS, disables CSRF protection, and specifies which endpoints require authentication. 
     * Publicly accessible endpoints are defined, while all other endpoints require JWT-based authentication via the custom JwtFilter.</p>
     *
     * @param http                  The HttpSecurity object used to define security policies
     * @return                      The configured SecurityFilterChain
     * 
     * @throws Exception            if any error occurs during the filter chain setup
     */
	@Bean
	SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
		http.cors(cors -> cors.configurationSource(corsConfigurationSource()))
			.csrf(csrf -> csrf.disable())
			.authorizeHttpRequests(
					authorizeRequests -> authorizeRequests
					.requestMatchers("/users/current", "/users/sign-in", "/users/sign-up", "/clothing-items", "/images/**").permitAll()
					.anyRequest().authenticated())
			.addFilterBefore(jwtFilter, org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter.class);;
		
		return http.build();
	}
	
     /**
     * Provides a bean for encoding passwords using the BCrypt hashing algorithm.
     *
     * @return                      A new BCryptPasswordEncoder instance
     */
    @Bean
    BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    } 
}
