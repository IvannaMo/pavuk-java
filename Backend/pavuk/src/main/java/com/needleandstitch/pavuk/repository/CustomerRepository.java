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
 * @author                            Needle & Stitch
 * @version                           1.0.0
 * @since                             15.12.2024
 */
public interface CustomerRepository extends JpaRepository<Customer, Long> {
	 /**
     * Finds a user by its email.
     * @param name 					  The email of the user to find
     */
	Optional<Customer> findByUserEmail(String email);
}
