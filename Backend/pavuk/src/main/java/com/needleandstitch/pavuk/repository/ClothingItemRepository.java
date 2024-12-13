package com.needleandstitch.pavuk.repository;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import com.needleandstitch.pavuk.model.ClothingItem;


public interface ClothingItemRepository extends JpaRepository<ClothingItem, Long> {
	public Optional<ClothingItem> findByName(String name);
}
