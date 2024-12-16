package com.needleandstitch.pavuk.data;

import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import com.needleandstitch.pavuk.model.Role;
import com.needleandstitch.pavuk.repository.RoleRepository;

/**
 * Seeder class for initializing default roles in the database.
 * This class checks that certain roles are present in the database and adds
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
@Order(1)
public class RoleSeeder implements CommandLineRunner {
    /**
     * Repository for performing CRUD operations on roles.
     */
    private final RoleRepository roleRepository;

    /**
     * Constructs a new RoleSeeder with the specified repository.
     *
     * @param roleRepository The repository used for interacting with the Role entities
     */
    public RoleSeeder(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    /**
     * Seeds the database with default roles if they are not already present.
     *
     * @param args          The command-line arguments passed to the application
     * 
     * @throws Exception    if any error occurs during the seeding process
     */
    @Override
    public void run(String... args) throws Exception {
        if (roleRepository.findByName("Admin").isEmpty()) {
            roleRepository.save(new Role("Admin"));
        }
        if (roleRepository.findByName("Moderator").isEmpty()) {
            roleRepository.save(new Role("Moderator"));
        }
        if (roleRepository.findByName("User").isEmpty()) {
            roleRepository.save(new Role("User"));
        }
    }
}
