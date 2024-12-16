package com.needleandstitch.pavuk.repository;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import com.needleandstitch.pavuk.model.ClothingItem;

/**
 * Repository interface for managing ClothingItem entities.
 * <p>
 * This interface provides methods for performing CRUD operations
 * and additional query methods for interacting with the database.
 * </p>
 * 
 * @author                            Needle &amp; Stitch
 * @version                           1.0.0
 * @since                             15.12.2024
 */
public interface ClothingItemRepository extends JpaRepository<ClothingItem, Long> {
	 /**
     * Finds a clothing item by its name.
     * @param name 					  The name of the clothing item to find
     * @return						  An Optional containing the found Category if present, otherwise an empty Optional.
     */
	public Optional<ClothingItem> findByName(String name);
}
