package com.needleandstitch.pavuk.repository;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import com.needleandstitch.pavuk.model.Color;

/**
 * Repository interface for managing Color entities.
 * <p>
 * This interface provides methods for performing CRUD operations
 * and additional query methods for interacting with the database.
 * </p>
 * 
 * @author                            Needle & Stitch
 * @version                           1.0.0
 * @since                             15.12.2024
 */
public interface ColorRepository extends JpaRepository<Color, Long> {
	 /**
     * Finds a color by its hex code.
     * @param name 					  The hex code of the color to find
     */
	public Optional<Color> findByHex(String hex);
}
