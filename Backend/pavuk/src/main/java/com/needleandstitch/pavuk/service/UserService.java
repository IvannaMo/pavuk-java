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


@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;
    
    @PersistenceContext
    private EntityManager entityManager;

    @Transactional(readOnly = true)
    public User findById(Long id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("User not found: " + id));
    }
    
    @Transactional(readOnly = true)
    public User findByEmail(String email) {
    	return userRepository.findByEmail(email)
                .orElseThrow(() -> new EntityNotFoundException("User not found with email: " + email));
    }

    @Transactional(readOnly = true)
    public List<User> findAll() {
        return userRepository.findAll();
    }

    @Transactional
    public User createUser(String firstName, String lastName, LocalDate dateOfBirth, String phone, String email, Boolean newsletterSubscription, String password, Role role) {
        User newUser = new User(firstName, lastName, dateOfBirth, phone, email, newsletterSubscription, passwordEncoder.encode(password), role);
        userRepository.save(newUser);
        
        return newUser;
    }

    @Transactional(readOnly = true)
    public User signInUser(String email, String password) {
    	User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new EntityNotFoundException("User not found with email: " + email));
        if (!passwordEncoder.matches(password, user.getPassword())) {
            throw new IllegalArgumentException("Invalid credentials");
        }

        return user;
    }
    
    @Transactional
    public void updateUser(Long id, String email) {
        User user = findById(id);
        user.setEmail(email);
        userRepository.save(user);
    }

    @Transactional(isolation = Isolation.READ_COMMITTED)
    public void deleteUser(Long id) {
        if (!userRepository.existsById(id)) {
            throw new EntityNotFoundException("User not found: " + id);
        }
        userRepository.deleteById(id);
    }
}
