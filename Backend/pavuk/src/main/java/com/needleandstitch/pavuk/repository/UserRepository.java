package com.needleandstitch.pavuk.repository;

import com.needleandstitch.pavuk.model.User;
import org.springframework.data.jpa.repository.JpaRepository;


public interface UserRepository extends JpaRepository<User, Long> {
	public User findByEmail(String email);
}