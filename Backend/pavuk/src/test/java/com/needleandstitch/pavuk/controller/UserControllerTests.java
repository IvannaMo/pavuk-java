package com.needleandstitch.pavuk.controller;

import com.needleandstitch.pavuk.model.User;
import com.needleandstitch.pavuk.service.RoleService;
import com.needleandstitch.pavuk.service.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import jakarta.persistence.EntityNotFoundException;
import jakarta.servlet.http.HttpServletResponse;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@SpringBootTest
@ExtendWith(MockitoExtension.class)
class UserControllerTest {
    @InjectMocks
    private UserController userController;

    @MockBean
    private UserService userService;

    @MockBean
    private RoleService roleService;

    private User user;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);

        user = new User();
        user.setId(1L);
        user.setFirstName("John");
        user.setLastName("Doe");
        user.setEmail("john.doe@example.com");
    }

    @Test
    void testGetAllUsers() {
        List<User> users = Arrays.asList(user);
        when(userService.findAll()).thenReturn(users);

        ResponseEntity<List<User>> response = userController.getAllUsers();

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals(1, response.getBody().size());
        assertEquals("John", response.getBody().get(0).getFirstName());
    }

    @Test
    void testGetUserById_Success() {
        when(userService.findById(1L)).thenReturn(user);

        ResponseEntity<User> response = userController.getUserById(1L);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals("John", response.getBody().getFirstName());
    }

    @Test
    void testGetUserById_NotFound() {
        when(userService.findById(1L)).thenThrow(new EntityNotFoundException("User not found"));

        ResponseEntity<User> response = userController.getUserById(1L);

        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
    }

    @Test
    void testCreateUser() {
        when(roleService.findByName("User")).thenReturn(null);

        ResponseEntity<Void> response = userController.createUser(user, mock(HttpServletResponse.class));

        assertEquals(HttpStatus.CREATED, response.getStatusCode());
    }

    @Test
    void testUpdateUser_Success() {
        doNothing().when(userService).updateUser(1L, "new.email@example.com");

        User userDetails = new User();
        userDetails.setEmail("new.email@example.com");

        ResponseEntity<Void> response = userController.updateUser(1L, userDetails);

        assertEquals(HttpStatus.OK, response.getStatusCode());
    }

    @Test
    void testUpdateUser_NotFound() {
        doThrow(new EntityNotFoundException("User not found")).when(userService).updateUser(1L,
                "new.email@example.com");

        User userDetails = new User();
        userDetails.setEmail("new.email@example.com");

        ResponseEntity<Void> response = userController.updateUser(1L, userDetails);

        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
    }

    @Test
    void testDeleteUser_Success() {
        doNothing().when(userService).deleteUser(1L);

        ResponseEntity<Void> response = userController.deleteUser(1L);

        assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
    }

    @Test
    void testDeleteUser_NotFound() {
        doThrow(new EntityNotFoundException("User not found")).when(userService).deleteUser(1L);

        ResponseEntity<Void> response = userController.deleteUser(1L);

        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
    }
}