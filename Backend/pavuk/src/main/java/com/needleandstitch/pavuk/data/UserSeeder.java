package com.needleandstitch.pavuk.data;

import java.time.LocalDate;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
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
    private final BCryptPasswordEncoder passwordEncoder;

    public UserSeeder(UserRepository userRepository, RoleRepository roleRepository, BCryptPasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.passwordEncoder = passwordEncoder;
    }
    
    @Override
    public void run(String... args) throws Exception {
    	Role role = roleRepository.findByName("User").orElseGet(() -> {
    		return roleRepository.save(new Role("User"));
    	});  
    	
    	if (role != null) {
    		userRepository.save(new User("John", "Doe", LocalDate.of(2000, 1, 1), "+380000000000", "john.doe@example.com", true, passwordEncoder.encode("12345678"), role));
    		userRepository.save(new User("Jane", "Doe", LocalDate.of(2004, 10, 1), "+380000000000", "jane.doe@example.com", true, passwordEncoder.encode("12345678"), role));
    		userRepository.save(new User("Bob", "Doe", LocalDate.of(2002, 8, 1), "+380000000000", "bob.doe@example.com", true, passwordEncoder.encode("12345678"), role));
    		userRepository.save(new User("Kate", "Doe", LocalDate.of(2001, 4, 1), "+380000000000", "kate.doe@example.com", true, passwordEncoder.encode("12345678"), role));
    		userRepository.save(new User("Alice", "Doe", LocalDate.of(2003, 6, 1), "+380000000000", "alice.doe@example.com", true, passwordEncoder.encode("12345678"), role));
    	}
    }
}
