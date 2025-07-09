package com.needleandstitch.pavuk.repository;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import com.needleandstitch.pavuk.model.ShippingInfo;

/**
 * Repository interface for managing ShippingInfo entities.
 * <p>
 * This interface provides methods for performing CRUD operations
 * and additional query methods for interacting with the database.
 * </p>
 * 
 * @author                            Needle &amp; Stitch
 * @version                           1.0.0
 * @since                             15.12.2024
 */
public interface ShippingInfoRepository extends JpaRepository<ShippingInfo, Long>  {
	 /**
     * Finds a user shipping information by its postal code.
     * @param postalCode 			The postal code of the shipping information to find
     * @return						An Optional containing the found ShippingInfo if present, otherwise an empty Optional.
     */
	public Optional<ShippingInfo> findByPostalCode(String postalCode);
}