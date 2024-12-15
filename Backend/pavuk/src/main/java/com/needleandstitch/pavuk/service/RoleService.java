package com.needleandstitch.pavuk.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.needleandstitch.pavuk.model.Role;
import com.needleandstitch.pavuk.repository.RoleRepository;
import jakarta.persistence.EntityNotFoundException;

/**
 * Service layer for managing roles.
 * <p>
 * This class provides business logic for operations related to roles, 
 * such as retrieving, creating, updating, and deleting roles.
 * </p>
 * 
 * <h2>Usage:</h2>
 * <ul>
 *   <li>Retrieve roles by ID or name</li>
 *   <li>Create new roles</li>
 *   <li>Update existing roles</li>
 *   <li>Delete roles</li>
 * </ul>

 * 
 * @author                     Needle & Stitch
 * @version                    1.0.0
 * @since                      15.12.2024
 */
@Service
public class RoleService {
     /**
     * Repository class for performing CRUD operations on roles.
     */
	@Autowired
    private RoleRepository roleRepository;
	
    /**
     * Finds a role by its name.
     *
     * @param name                      The name of the role to find
     * @return                          The role with the specified name
     * 
     * @throws EntityNotFoundException if the role with the specified name is not found
     */
	@Transactional(readOnly = true)
    public Role findByName(String name) {
        return roleRepository.findByName(name)
                .orElseThrow(() -> new EntityNotFoundException("Role not found: " + name));
    }

    /**
     * Finds a role by its ID.
     *
     * @param id                        The ID of the role to find
     * @return                          The role with the specified ID
     * 
     * @throws EntityNotFoundException if the role is not found
     */
    @Transactional(readOnly = true)
    public Role findById(Long id) {
        return roleRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Role not found: " + id));
    }

    /**
     * Retrieves all roles.
     *
     * @return                          A list of all roles
     */
    @Transactional(readOnly = true)
    public List<Role> findAll() {
        return roleRepository.findAll();
    }

    /**
     * Creates a new role.
     *
     * @param name                      The name of the role
     * @return                          The newly created role
     */
    @Transactional
    public Role createRole(String name) {
        Role newRole = new Role(name);
        return roleRepository.save(newRole);
    }

    /**
     * Updates an existing role.
     *
     * @param id                        The ID of the role to update
     * @param name                      The new name of the role
     * @return                          The updated role
     * 
     */
    @Transactional
    public Role updateRole(Long id, String name) {
        Role role = findById(id);
        role.setName(name);
        return roleRepository.save(role);
    }

    /**
     * Deletes a role by its ID.
     *
     * @param id                        The ID of the role to delete
     * 
     * @throws EntityNotFoundException if the role is not found
     */
    @Transactional
    public void deleteRole(Long id) {
        if (!roleRepository.existsById(id)) {
            throw new EntityNotFoundException("Role not found: " + id);
        }
        roleRepository.deleteById(id);
    }
}
