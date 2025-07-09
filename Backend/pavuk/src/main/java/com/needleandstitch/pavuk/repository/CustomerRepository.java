package com.needleandstitch.pavuk.repository;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import com.needleandstitch.pavuk.model.Customer;

/**
 * Repository interface for managing Customer entities.
 * <p>
 * This interface provides methods for performing CRUD operations
 * and additional query methods for interacting with the database.
 * </p>
 * 
 * @author                            Needle &amp; Stitch
 * @version                           1.0.0
 * @since                             15.12.2024
 */
public interface CustomerRepository extends JpaRepository<Customer, Long> {
	 /**
     * Finds a user by its email.
     * @param email 				The email of the user to find
     * @return						An Optional containing the found Customer if present, otherwise an empty Optional.
     */
	Optional<Customer> findByUserId(Long id);
	Optional<Customer> findByUserEmail(String email);
}
