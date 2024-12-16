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
 * @author                            Needle &amp; Stitch
 * @version                           1.0.0
 * @since                             15.12.2024
 */
public interface ColorRepository extends JpaRepository<Color, Long> {
	 /**
     * Finds a color by its hex code.
     * @param hex 					  The hex code of the color to find
     * @return						  An Optional containing the found Color if present, otherwise an empty Optional.
     */
	public Optional<Color> findByHex(String hex);
}
