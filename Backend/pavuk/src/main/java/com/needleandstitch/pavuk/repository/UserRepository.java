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
 * @author                            Needle &amp; Stitch
 * @version                           1.0.0
 * @since                             15.12.2024
 */
public interface UserRepository extends JpaRepository<User, Long> {
	 /**
     * Finds a user by its email.
     * @param email 				The email of the user to find
     * @return						An Optional containing the found User if present, otherwise an empty Optional.
     */
	public Optional<User> findByEmail(String email);
}