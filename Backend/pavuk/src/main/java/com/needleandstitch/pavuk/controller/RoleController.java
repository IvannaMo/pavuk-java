package com.needleandstitch.pavuk.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import com.needleandstitch.pavuk.model.Role;
import com.needleandstitch.pavuk.service.RoleService;
import jakarta.persistence.EntityNotFoundException;


/**
 * REST controller for managing Role objects.
 * <p>
 * This class provides endpoints for handling role-related operations such as
 * retrieving roles, creating new roles, updating existing roles, and deleting roles.
 * </p>
 * 
 * <h2>Endpoints:</h2>
 * <ul>
 *   <li>GET /roles             Retrieve all roles</li>
 *   <li>GET /roles/{id}        Retrieve a role by its ID</li>
 *   <li>GET /roles/name/{name} Retrieve a role by its name</li>
 *   <li>POST /roles            Create a new role</li>
 *   <li>PUT /roles/{id}        Update an existing role</li>
 *   <li>DELETE /roles/{id}     Delete a role</li>
 * </ul>
 * 
 * @author                      Needle &amp; Stitch
 * @version                     1.0.0
 * @since                       15.12.2024
 */
@Controller 
@RequestMapping("/roles")
public class RoleController {

    /**
     * Service class for handling role-related operations.
     */
    @Autowired
    private RoleService roleService;

    /**
     * Retrieves all roles.
     *
     * @return                  A ResponseEntity with a 200 OK status, a list of all roles
     */
    @GetMapping
    public ResponseEntity<List<Role>> getAllRoles() {
        List<Role> roles = roleService.findAll();
        return ResponseEntity.ok(roles);
    }

    /**
     * Retrieves a role by its ID.
     *
     * @param id                The ID of the role to retrieve
     * @return                  A ResponseEntity with a 200 OK status with a role if successful, or a 404 Not Found status if not.
     */
    @GetMapping("/{id}")
    public ResponseEntity<Role> getRoleById(@PathVariable Long id) {
        try {
            Role role = roleService.findById(id);
            return ResponseEntity.ok(role);
        } catch (EntityNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    /**
     * Retrieves a role by its name.
     *
     * @param name              The name of the role to retrieve
     * @return                  A ResponseEntity with a 200 OK status with a role if successful, or a 404 Not Found status if not.
     */
    @GetMapping("/name/{name}")
    public ResponseEntity<Role> getRoleByName(@PathVariable String name) {
        try {
            Role role = roleService.findByName(name);
            return ResponseEntity.ok(role);
        } catch (EntityNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    /**
     * Creates a new role.
     *
     * @param role              The role to create
     * @return                  A ResponseEntity with a status of 201 (CREATED).
     */
    @PostMapping
    public ResponseEntity<Role> createRole(@RequestBody Role role) {
        Role createdRole = roleService.createRole(role.getName());
        return ResponseEntity.status(HttpStatus.CREATED).body(createdRole);
    }

    /**
     * Updates an existing role's name.
     *
     * @param id                The ID of the role to update
     * @param role              The updated role object
     * @return                  A ResponseEntity with a 200 OK status if successful, or a 404 Not Found status if not.
     */
    @PutMapping("/{id}")
    public ResponseEntity<Role> updateRole(@PathVariable Long id, @RequestBody Role role) {
        try {
            Role updatedRole = roleService.updateRole(id, role.getName());
            return ResponseEntity.ok(updatedRole);
        } catch (EntityNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    /**
     * Deletes a role by its ID.
     *
     * @param id the ID of the role to delete
     * @return              A ResponseEntity with a 204 No Content status if successful, or a 404 Not Found status if not.
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteRole(@PathVariable Long id) {
        try {
            roleService.deleteRole(id);
            return ResponseEntity.noContent().build();
        } catch (EntityNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }
}