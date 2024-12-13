package com.needleandstitch.pavuk.repository;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import com.needleandstitch.pavuk.model.ShippingInfo;


public interface ShippingInfoRepository extends JpaRepository<ShippingInfo, Long>  {
	public Optional<ShippingInfo> findByPostalCode(String postalCode);
}