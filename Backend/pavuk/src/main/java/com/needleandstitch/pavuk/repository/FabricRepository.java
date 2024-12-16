package com.needleandstitch.pavuk.repository;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import com.needleandstitch.pavuk.model.Fabric;

/**
 * Repository interface for managing Fabric entities.
 * <p>
 * This interface provides methods for performing CRUD operations
 * and additional query methods for interacting with the database.
 * </p>
 * 
 * @author                            Needle &amp; Stitch
 * @version                           1.0.0
 * @since                             15.12.2024
 */
public interface FabricRepository extends JpaRepository<Fabric, Long> {
	 /**
     * Finds a fabric by its name.
     * @param name 					  The name of the fabric to find
     * @return						  An Optional containing the found Fabric if present, otherwise an empty Optional.
     */
	public Optional<Fabric> findByName(String name);
}
