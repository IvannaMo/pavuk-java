package com.needleandstitch.pavuk.data;

import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import com.needleandstitch.pavuk.model.Role;
import com.needleandstitch.pavuk.repository.RoleRepository;


@Component
@Order(1)
public class RoleSeeder implements CommandLineRunner {
	private final RoleRepository roleRepository;

    public RoleSeeder(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }
    
    @Override
    public void run(String... args) throws Exception {
    	if (roleRepository.findByName("Admin") == null) {
    		roleRepository.save(new Role("Admin"));
    	}
    	if (roleRepository.findByName("Moderator") == null) {
    		roleRepository.save(new Role("Moderator"));
    	}
    	if (roleRepository.findByName("User") == null) {
    		roleRepository.save(new Role("User"));
    	}
    }
}
