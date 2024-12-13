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


@Controller
@RequestMapping("/users")
@CrossOrigin
public class UserController {
    @Autowired
    private UserService userService;
    
    @Autowired
    private RoleService roleService;
    
    @Autowired
    private JwtUtil jwtUtil;

    @GetMapping
    public ResponseEntity<List<User>> getAllUsers() {
        List<User> users = userService.findAll();
        return ResponseEntity.ok(users);
    }

    @GetMapping("/{id}")
    public ResponseEntity<User> getUserById(@PathVariable Long id) {
        try {
            User user = userService.findById(id);
            return ResponseEntity.ok(user);
        } catch (EntityNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }
    
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
    
    @PostMapping("/sign-out")
    public ResponseEntity<Void> signOut(HttpServletResponse response) {
        Cookie cookie = new Cookie("jwt", null);
        cookie.setHttpOnly(true);
        cookie.setPath("/");
        cookie.setMaxAge(0);
        response.addCookie(cookie);

        return ResponseEntity.ok().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> updateUser(@PathVariable Long id, @RequestBody User userDetails) {
        try {
            userService.updateUser(id, userDetails.getEmail());
            return ResponseEntity.ok().build();
        } catch (EntityNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

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
