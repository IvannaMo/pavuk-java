package com.needleandstitch.pavuk.repository;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import com.needleandstitch.pavuk.model.PostService;


public interface PostServiceRepository extends JpaRepository<PostService, Long> {
	public Optional<PostService> findByName(String name);
}
