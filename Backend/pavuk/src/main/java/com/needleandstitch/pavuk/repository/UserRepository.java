package com.needleandstitch.pavuk.repository;

import java.util.Optional;
import com.needleandstitch.pavuk.model.User;
import org.springframework.data.jpa.repository.JpaRepository;


public interface UserRepository extends JpaRepository<User, Long> {
	public Optional<User> findByEmail(String email);
}