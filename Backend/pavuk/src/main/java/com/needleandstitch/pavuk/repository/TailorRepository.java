package com.needleandstitch.pavuk.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.needleandstitch.pavuk.model.Tailor;

/**
 * Repository interface for managing Tailor entities.
 * <p>
 * This interface provides methods for performing CRUD operations
 * and additional query methods for interacting with the database.
 * </p>
 * 
 * @author                            Needle & Stitch
 * @version                           1.0.0
 * @since                             15.12.2024
 */
public interface TailorRepository extends JpaRepository<Tailor, Long> {}
