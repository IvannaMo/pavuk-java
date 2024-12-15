package com.needleandstitch.pavuk.repository;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import com.needleandstitch.pavuk.model.Category;

/**
 * Repository interface for managing Category entities.
 * <p>
 * This interface provides methods for performing CRUD operations
 * and additional query methods for interacting with the database.
 * </p>
 * 
 * @author                            Needle & Stitch
 * @version                           1.0.0
 * @since                             15.12.2024
 */
public interface CategoryRepository extends JpaRepository<Category, Long> {
	 /**
     * Finds a category by its name.
     * @param name 					  The name of the category to find
     */
	public Optional<Category> findByName(String name);
}
