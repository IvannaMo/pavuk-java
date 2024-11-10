package com.needleandstitch.pavuk.data;

import java.time.LocalDate;

import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import com.needleandstitch.pavuk.model.Role;
import com.needleandstitch.pavuk.model.User;
import com.needleandstitch.pavuk.repository.RoleRepository;
import com.needleandstitch.pavuk.repository.UserRepository;


@Component
@Order(2)
public class UserSeeder implements CommandLineRunner {
	private final UserRepository userRepository;
    private final RoleRepository roleRepository;

    public UserSeeder(UserRepository userRepository, RoleRepository roleRepository) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
    }
    
    @Override
    public void run(String... args) throws Exception {
    	Role role = roleRepository.findByName("User");  
    	if (role != null) {
    		userRepository.save(new User("John", "Doe", "john.doe@example.com", "+380000000000", "12345678", LocalDate.of(2000, 1, 1), true, role));
    		userRepository.save(new User("Jane", "Doe", "jane.doe@example.com", "+380000000000", "12345678", LocalDate.of(2004, 10, 1), true, role));
    		userRepository.save(new User("Bob", "Doe", "bob.doe@example.com", "+380000000000", "12345678", LocalDate.of(2002, 8, 1), true, role));
    		userRepository.save(new User("Kate", "Doe", "kate.doe@example.com", "+380000000000", "12345678", LocalDate.of(2001, 4, 1), true, role));
    		userRepository.save(new User("Alice", "Doe", "alice.doe@example.com", "+380000000000", "12345678", LocalDate.of(2003, 6, 1), true, role));
    	}
    }
}
