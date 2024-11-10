package com.needleandstitch.pavuk.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.needleandstitch.pavuk.model.Role;


public interface RoleRepository extends JpaRepository<Role, Long> {
	Role findByName(String name);
}
