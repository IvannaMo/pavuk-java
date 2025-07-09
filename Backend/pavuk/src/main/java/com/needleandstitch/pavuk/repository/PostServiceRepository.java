package com.needleandstitch.pavuk.repository;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import com.needleandstitch.pavuk.model.PostService;

/**
 * Repository interface for managing PostService entities.
 * <p>
 * This interface provides methods for performing CRUD operations
 * and additional query methods for interacting with the database.
 * </p>
 * 
 * @author                            Needle &amp; Stitch
 * @version                           1.0.0
 * @since                             15.12.2024
 */
public interface PostServiceRepository extends JpaRepository<PostService, Long> {
	 /**
     * Finds a post service by its name.
     * @param name 					  The name of the post service to find
     * @return						  An Optional containing the found PostService if present, otherwise an empty Optional.
     */
	public Optional<PostService> findByName(String name);
}
