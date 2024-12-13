package com.needleandstitch.pavuk.repository;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import com.needleandstitch.pavuk.model.Color;


public interface ColorRepository extends JpaRepository<Color, Long> {
	public Optional<Color> findByHex(String hex);
}
