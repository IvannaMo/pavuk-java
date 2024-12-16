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

/**
 * Seeder class for initializing default users in the database.
 * This class checks that certain users are present in the database and adds
 * them if they are missing.
 *
 * <p>
 * The seeding process is triggered during application startup.
 * </p>
 *
 *
 * @author                  Needle &amp; Stitch
 * @version                 1.0.0
 * @since                   15.12.2024
 */
@Component
@Order(2)
public class UserSeeder implements CommandLineRunner {
    /**
     * Repository for performing CRUD operations on users.
     */
    private final UserRepository userRepository;
    /**
     * Repository for performing CRUD operations on roles.
     */
    private final RoleRepository roleRepository;
    /**
     * Password encoder for hashing and verifying passwords using BCrypt.
     */
    private final BCryptPasswordEncoder passwordEncoder;

    /**
     * Constructs a new UserSeeder with the specified repository.
     *
     * @param userRepository  The repository used for interacting with the User entities
     * @param roleRepository  The repository used for interacting with the Role entities
     * @param passwordEncoder The password encoder for hashing and verifying passwords
     */
    public UserSeeder(UserRepository userRepository, RoleRepository roleRepository,
            BCryptPasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.passwordEncoder = passwordEncoder;
    }

    /**
     * Seeds the database with default users if they are not already present.
     *
     * @param args          The command-line arguments passed to the application
     * 
     * @throws Exception    if any error occurs during the seeding process
     */
    @Override
    public void run(String... args) throws Exception {
        Role role = roleRepository.findByName("User").orElseGet(() -> {
            return roleRepository.save(new Role("User"));
        });

        if (role != null) {
            userRepository.save(new User("John", "Doe", LocalDate.of(2000, 1, 1), "+380000000000",
                    "john.doe@example.com", true, passwordEncoder.encode("12345678"), role));
            userRepository.save(new User("Jane", "Doe", LocalDate.of(2004, 10, 1), "+380000000000",
                    "jane.doe@example.com", true, passwordEncoder.encode("12345678"), role));
            userRepository.save(new User("Bob", "Doe", LocalDate.of(2002, 8, 1), "+380000000000", "bob.doe@example.com",
                    true, passwordEncoder.encode("12345678"), role));
            userRepository.save(new User("Kate", "Doe", LocalDate.of(2001, 4, 1), "+380000000000",
                    "kate.doe@example.com", true, passwordEncoder.encode("12345678"), role));
            userRepository.save(new User("Alice", "Doe", LocalDate.of(2003, 6, 1), "+380000000000",
                    "alice.doe@example.com", true, passwordEncoder.encode("12345678"), role));
        }
    }
}
