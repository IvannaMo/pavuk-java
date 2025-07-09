package com.needleandstitch.pavuk.repository;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import com.needleandstitch.pavuk.model.Role;

/**
 * Repository interface for managing Role entities.
 * <p>
 * This interface provides methods for performing CRUD operations
 * and additional query methods for interacting with the database.
 * </p>
 * 
 * @author                            Needle &amp; Stitch
 * @version                           1.0.0
 * @since                             15.12.2024
 */
public interface RoleRepository extends JpaRepository<Role, Long> {
	 /**
     * Finds a role by its name.
     * @param name 					  The name of the role to find
     * @return						  An Optional containing the found Role if present, otherwise an empty Optional.
     */
	public Optional<Role> findByName(String name);
}
