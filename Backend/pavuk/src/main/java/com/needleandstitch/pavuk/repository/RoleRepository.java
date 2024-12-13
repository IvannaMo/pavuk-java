package com.needleandstitch.pavuk.repository;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import com.needleandstitch.pavuk.model.Role;


public interface RoleRepository extends JpaRepository<Role, Long> {
	public Optional<Role> findByName(String name);
}
