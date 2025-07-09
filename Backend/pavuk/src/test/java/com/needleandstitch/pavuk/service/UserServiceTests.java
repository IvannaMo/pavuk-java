package com.needleandstitch.pavuk.service;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;

import com.needleandstitch.pavuk.model.Role;
import com.needleandstitch.pavuk.model.User;
import com.needleandstitch.pavuk.repository.UserRepository;

import jakarta.persistence.EntityNotFoundException;

import java.util.*;
import java.time.LocalDate;

public class UserServiceTests {

    @InjectMocks
    private UserService userService;

    @Mock
    private UserRepository userRepository;

    private User user;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);

        user = new User();
        user.setId(1L);
        user.setFirstName("John");
        user.setLastName("Doe");
        user.setEmail("john.doe@example.com");
        user.setDateOfBirth(LocalDate.of(1990, 1, 1));
        user.setPhone("+380000000000");
        user.setNewsletterSubscription(true);
        user.setPassword("12345678");
        user.setRole(new Role("ROLE_USER"));
    }

    @Test
    public void testFindById_Success() {
        when(userRepository.findById(1L)).thenReturn(Optional.of(user));

        User result = userService.findById(1L);

        assertNotNull(result);
        assertEquals("John", result.getFirstName());
        verify(userRepository, times(1)).findById(1L);
    }

    @Test
    public void testFindById_UserNotFound() {
        when(userRepository.findById(1L)).thenReturn(Optional.empty());

        EntityNotFoundException exception = assertThrows(EntityNotFoundException.class, () -> {
            userService.findById(1L);
        });

        assertEquals("User not found: 1", exception.getMessage());
    }

    @Test
    public void testFindAll() {
        List<User> users = Arrays.asList(user);
        when(userRepository.findAll()).thenReturn(users);

        List<User> result = userService.findAll();

        assertNotNull(result);
        assertEquals(1, result.size());
        assertEquals("John", result.get(0).getFirstName());
        verify(userRepository, times(1)).findAll();
    }

    @Test
    public void testCreateUser() {
        when(userRepository.save(any(User.class))).thenReturn(user);

        userService.createUser("John", "Doe", LocalDate.of(1990, 1, 1), "123456789", "john.doe@example.com", true,
                "password", new Role("USER"));

        verify(userRepository, times(1)).save(any(User.class));
    }

    @Test
    public void testUpdateUser_Success() {
        when(userRepository.findById(1L)).thenReturn(Optional.of(user));
        userService.updateUser(
        	1L,
        	"Test",
        	"Doe",
        	LocalDate.of(2000, 1, 1),
           	"+380000000000",
          	"test.doe@example.com",
            true
        );

        assertEquals("Test", user.getFirstName());
        assertEquals("Doe", user.getLastName());
        assertEquals(LocalDate.of(2000, 1, 1), user.getDateOfBirth());
        assertEquals("+380000000000", user.getPhone());
        assertEquals("test.doe@example.com", user.getEmail());
        assertTrue(user.getNewsletterSubscription());
        verify(userRepository, times(1)).save(user);
    }

    @Test
    public void testUpdateUser_UserNotFound() {
        when(userRepository.findById(1L)).thenReturn(Optional.empty());

        EntityNotFoundException exception = assertThrows(EntityNotFoundException.class, () -> {
            userService.updateUser(
            	1L,
             	"Test",
              	"Doe",
              	LocalDate.of(2000, 1, 1),
            	"+380000000000",
              	"test.doe@example.com",
               	true
            );
        });

        assertEquals("User not found: 1", exception.getMessage());
    }

    @Test
    public void testDeleteUser_Success() {
        when(userRepository.existsById(1L)).thenReturn(true);

        userService.deleteUser(1L);

        verify(userRepository, times(1)).deleteById(1L);
    }

    @Test
    public void testDeleteUser_UserNotFound() {
        when(userRepository.existsById(1L)).thenReturn(false);

        EntityNotFoundException exception = assertThrows(EntityNotFoundException.class, () -> {
            userService.deleteUser(1L);
        });

        assertEquals("User not found: 1", exception.getMessage());
    }
}