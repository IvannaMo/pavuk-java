package com.needleandstitch.pavuk.service;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityNotFoundException;
import jakarta.persistence.PersistenceContext;
import java.time.LocalDate;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;
import com.needleandstitch.pavuk.model.Role;
import com.needleandstitch.pavuk.model.User;
import com.needleandstitch.pavuk.repository.UserRepository;

/**
 * Service layer for managing users.
 * <p>
 * This class provides business logic for operations related to users, 
 * such as retrieving, creating, updating, and deleting users.
 * </p>
 * 
 * <h2>Usage:</h2>
 * <ul>
 *   <li>Retrieve roles by ID or email</li>
 *   <li>Create new users</li>
 *   <li>Update existing users</li>
 *   <li>Delete users</li>
 *   <li>Handle user authentication</li>
 * </ul>

 * 
 * @author                     Needle &amp; Stitch
 * @version                    1.0.0
 * @since                      15.12.2024
 */
@Service
public class UserService {
    /**
     * Repository class for performing CRUD operations on users.
     */
    @Autowired
    private UserRepository userRepository;

    /**
     * Password encoder for hashing and verifying passwords using BCrypt.
     */
    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

     /**
     * EntityManager used for interacting with the persistence context.
     */
    @PersistenceContext
    private EntityManager entityManager;

     /**
     * Finds a user by their ID.
     *
     * @param id                        The ID of the user to find
     * @return                          The user with the specified ID
     * @throws EntityNotFoundException if the user is not found
     */
    @Transactional(readOnly = true)
    public User findById(Long id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("User not found: " + id));
    }
    
    /**
     * Finds a user by their email address.
     *
     * @param email                     The email address of the user to find
     * @return                          The user with the specified email
     * 
     * @throws EntityNotFoundException if the user is not found
     */
    @Transactional(readOnly = true)
    public User findByEmail(String email) {
    	return userRepository.findByEmail(email)
                .orElseThrow(() -> new EntityNotFoundException("User not found with email: " + email));
    }

    /**
     * Retrieves all users from the database.
     *
     * @return                          A list of all users
     */
    @Transactional(readOnly = true)
    public List<User> findAll() {
        return userRepository.findAll();
    }

    /**
     * Creates a new user.
     *
     * @param firstName                 The first name of the user
     * @param lastName                  The last name of the user
     * @param dateOfBirth               The date of birth of the user
     * @param phone                     The phone number of the user
     * @param email                     The email address of the user
     * @param newsletterSubscription    Whether the user is subscribed to the newsletter
     * @param password                  The password of the user
     * @param role                      The role assigned to the user
     * @return                          The newly created user
     */
    @Transactional
    public User createUser(String firstName, String lastName, LocalDate dateOfBirth, String phone, String email, Boolean newsletterSubscription, String password, Role role) {
        User newUser = new User(firstName, lastName, dateOfBirth, phone, email, newsletterSubscription, passwordEncoder.encode(password), role);
        userRepository.save(newUser);
        
        return newUser;
    }

     /**
     * Authenticates a user by email and password.
     *
     * @param email                     The email address of the user
     * @param password                  The password of the user
     * @return                          The authenticated user
     * 
     * @throws EntityNotFoundException if the user with the specified email is not found
     * @throws IllegalArgumentException if the password does not match the stored password
     */
    @Transactional(readOnly = true)
    public User signInUser(String email, String password) {
    	User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new EntityNotFoundException("User not found with email: " + email));
        if (!passwordEncoder.matches(password, user.getPassword())) {
            throw new IllegalArgumentException("Invalid credentials");
        }

        return user;
    }
    
    /**
     * Updates the email address of a user.
     *
     * @param id                        The ID of the user to update
     * @param email                     The new email address for the user
     * 
     */
    @Transactional
    public void updateUser(Long id, String email) {
        User user = findById(id);
        user.setEmail(email);
        userRepository.save(user);
    }

     /**
     * Deletes a user by their ID.
     *
     * @param id                        The ID of the user to delete
     * 
     * @throws EntityNotFoundException if the user with the specified ID does not exist
     */
    @Transactional(isolation = Isolation.READ_COMMITTED)
    public void deleteUser(Long id) {
        if (!userRepository.existsById(id)) {
            throw new EntityNotFoundException("User not found: " + id);
        }
        userRepository.deleteById(id);
    }
}
