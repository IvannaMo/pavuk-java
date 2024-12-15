package com.needleandstitch.pavuk.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import com.needleandstitch.pavuk.model.Role;
import com.needleandstitch.pavuk.model.User;
import com.needleandstitch.pavuk.service.RoleService;
import com.needleandstitch.pavuk.service.UserService;
import com.needleandstitch.pavuk.security.JwtUtil;
import jakarta.persistence.EntityNotFoundException;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/**
 * REST controller for managing User objects.
 * <p>
 * This class provides endpoints for handling user-related operations such as
 * retrieving users, signing up new users, handling authentication, logging out users, updating existing users, and deleting users.
 * </p>
 * 
 * <h2>Endpoints:</h2>
 * <ul>
 *   <li>GET /users            Retrieve all roles</li>
 *   <li>GET /users/{id}       Retrieve a role by its ID</li>
 *   <li>GET /users/current    Retrive currently authenticated user</li>
 *   <li>POST /users/sign-up   Register a new user</li>
 *   <li>POST /users/sign-in   Sign in a user</li>
 *   <li>POST /users/sign-out  Log out current user</li>
 *   <li>PUT /users/{id}       Update an existing role</li>
 *   <li>DELETE /users/{id}    Delete a role</li>
 * </ul>
 * 
 * @author                     Needle & Stitch
 * @version                    1.0.0
 * @since                      15.12.2024
 */
@Controller
@RequestMapping("/users")
@CrossOrigin
public class UserController {

     /**
     * Service class for handling user-related operations.
     */
    @Autowired
    private UserService userService;

     /**
     * Service class for handling role-related operations.
     */
    @Autowired
    private RoleService roleService;
    
     /**
     * Utility class for handling JWT token operations.
     */
    @Autowired
    private JwtUtil jwtUtil;

    /**
     * Endpoint to retrieve all users.
     *
     * @return                A ResponseEntity containing a list of all User objects.
     * 
     */
    @GetMapping
    public ResponseEntity<List<User>> getAllUsers() {
        List<User> users = userService.findAll();
        return ResponseEntity.ok(users);
    }

    /**
     * Endpoint to retrieve a clothing item by its ID.
     *
     * @param id             The ID of the user.
     * @return               A ResponseEntity containing the User object if found or a 404 Not Found status if the object does not exist.
     * 
     */
    @GetMapping("/{id}")
    public ResponseEntity<User> getUserById(@PathVariable Long id) {
        try {
            User user = userService.findById(id);
            return ResponseEntity.ok(user);
        } catch (EntityNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }
    
     /**
     * Endpoint to retrieve the currently authenticated user based on the JWT token from cookies.
     * 
     * @param request       The HTTP request containing the cookies
     * @return              A ResponseEntity containing the current user and a status of OK (200) if authenticated, or UNAUTHORIZED (401) if not
     */
    @GetMapping("/current")
    public ResponseEntity<User> getCurrentUser(HttpServletRequest request) {
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
            User user = userService.findByEmail(email);
            return ResponseEntity.ok(user);
        }
        else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
    }

    /**
     * Endpoint to register a new user.
     * 
     * @param user          The user information to be used for the registration
     * @param response      The HTTP response for setting the JWT cookie
     * @return              A ResponseEntity with a CREATED (201) status
     */
    @PostMapping("/sign-up")
    public ResponseEntity<Void> createUser(@RequestBody User user, HttpServletResponse response) {
    	Role defaultRole = roleService.findByName("User");
    	
    	User newUser = userService.createUser(
        	user.getFirstName(),
        	user.getLastName(),
        	user.getDateOfBirth(),
        	user.getPhone(),
        	user.getEmail(),
        	user.getNewsletterSubscription(),
        	user.getPassword(),
        	defaultRole
        );
        
        String token = jwtUtil.generateToken(newUser);
		
		Cookie cookie = new Cookie("jwt", token);
        cookie.setHttpOnly(true);
        cookie.setSecure(true);
        cookie.setAttribute("SameSite", "None");
        cookie.setPath("/");
        cookie.setMaxAge(86400);
        response.addCookie(cookie);
        
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }
    
     /**
     * Endpoint to authenticate a user and return their information.
     * 
     * @param user         The user credentials (email and password)
     * @param response     The HTTP response for setting the JWT cookie
     * @return             A ResponseEntity containing the authenticated user and a status of OK (200) if successful, or UNAUTHORIZED (401) if authentication fails.
     */
    @PostMapping("/sign-in")
    public ResponseEntity<User> signIn(@RequestBody User user, HttpServletResponse response) {
    	try {
    		User authenticatedUser = userService.signInUser(user.getEmail(), user.getPassword());

    		String token = jwtUtil.generateToken(authenticatedUser);
    		
    		Cookie cookie = new Cookie("jwt", token);
            cookie.setHttpOnly(true);
            cookie.setSecure(true);
            cookie.setAttribute("SameSite", "None");
            cookie.setPath("/");
            cookie.setMaxAge(86400);
            response.addCookie(cookie);
    		
    		return ResponseEntity.ok(authenticatedUser);
    	} catch (EntityNotFoundException | IllegalArgumentException e) {
    		return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
    	}
    }

    /**
     * Endpoint to log the user out by removing the JWT cookie.
     * 
     * @param response      The HTTP response for removing the JWT cookie
     * @return              A ResponseEntity with a status of OK (200).
     */
    @PostMapping("/sign-out")
    public ResponseEntity<Void> signOut(HttpServletResponse response) {
        Cookie cookie = new Cookie("jwt", null);
        cookie.setHttpOnly(true);
        cookie.setPath("/");
        cookie.setMaxAge(0);
        response.addCookie(cookie);

        return ResponseEntity.ok().build();
    }

    /**
     * Endpoint to update an existing user by its ID.
     *
     * @param id            The ID of the user to update.
     * @param userDetails   The updated User object.
     * @return              A ResponseEntity with a 200 OK status if successful, or a 404 Not Found status if the object does not exist.
     * 
     */
    @PutMapping("/{id}")
    public ResponseEntity<Void> updateUser(@PathVariable Long id, @RequestBody User userDetails) {
        try {
            userService.updateUser(id, userDetails.getEmail());
            return ResponseEntity.ok().build();
        } catch (EntityNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    /**
     * Endpoint to delete a user by its ID.
     *
     * @param id            The ID of the user to delete.
     * @return              A ResponseEntity with a 204 No Content status if successful, or a 404 Not Found status if not.
     * 
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable Long id) {
        try {
            userService.deleteUser(id);
            return ResponseEntity.noContent().build();
        } catch (EntityNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }
}
