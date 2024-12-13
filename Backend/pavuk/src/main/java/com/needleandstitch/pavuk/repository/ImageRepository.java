package com.needleandstitch.pavuk.repository;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import com.needleandstitch.pavuk.model.Image;


public interface ImageRepository extends JpaRepository<Image, Long> {
	public Optional<Image> findByName(String name);
}
