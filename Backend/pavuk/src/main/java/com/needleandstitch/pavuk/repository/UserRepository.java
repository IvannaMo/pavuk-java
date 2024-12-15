package com.needleandstitch.pavuk.repository;

import java.util.Optional;
import com.needleandstitch.pavuk.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Repository interface for managing User entities.
 * <p>
 * This interface provides methods for performing CRUD operations
 * and additional query methods for interacting with the database.
 * </p>
 * 
 * @author                            Needle & Stitch
 * @version                           1.0.0
 * @since                             15.12.2024
 */
public interface UserRepository extends JpaRepository<User, Long> {
	 /**
     * Finds a user by its email.
     * @param name 					  The email of the user to find
     */
	public Optional<User> findByEmail(String email);
}