package com.needleandstitch.pavuk.repository;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import com.needleandstitch.pavuk.model.Image;

/**
 * Repository interface for managing Image entities.
 * <p>
 * This interface provides methods for performing CRUD operations
 * and additional query methods for interacting with the database.
 * </p>
 * 
 * @author                            Needle &amp; Stitch
 * @version                           1.0.0
 * @since                             15.12.2024
 */
public interface ImageRepository extends JpaRepository<Image, Long> {
	 /**
     * Finds an image by its name.
     * @param name 					  The name of the image to find
     * @return						  An Optional containing the found Image if present, otherwise an empty Optional.
     */
	public Optional<Image> findByName(String name);
}
