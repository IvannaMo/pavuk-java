package com.needleandstitch.pavuk.repository;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import com.needleandstitch.pavuk.model.Fabric;


public interface FabricRepository extends JpaRepository<Fabric, Long> {
	public Optional<Fabric> findByName(String name);
}
