package com.needleandstitch.pavuk.data;

import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import com.needleandstitch.pavuk.model.Tailor;
import com.needleandstitch.pavuk.model.User;
import com.needleandstitch.pavuk.repository.TailorRepository;
import com.needleandstitch.pavuk.repository.UserRepository;
import jakarta.persistence.EntityNotFoundException;

/**
 * Seeder class for initializing default tailors in the database.
 * This class checks that certain tailors are present in the database and adds
 * them if they are missing.
 *
 * <p>
 * The seeding process is triggered during application startup.
 * </p>
 *
 *
 * @author                              Needle &amp; Stitch
 * @version                             1.0.0
 * @since                               15.12.2024
 */
@Component
@Order(3)
public class TailorSeeder implements CommandLineRunner {
        /**
         * Repository for performing CRUD operations on tailors.
         */
        private final TailorRepository tailorRepository;

        /**
         * Repository for performing CRUD operations on users.
         */
        private final UserRepository userRepository;

        /**
         * Constructs a new TailorSeeder with the specified repository.
         *
         * @param tailorRepository      The repository used for interacting with the Tailor entities
         * @param userRepository        The repository used for interacting with the User entities
         */
        public TailorSeeder(TailorRepository tailorRepository, UserRepository userRepository) {
                this.tailorRepository = tailorRepository;
                this.userRepository = userRepository;
        }

        /**
         * Seeds the database with default tailors if they are not already present.
         *
         * @param args                  The command-line arguments passed to the application
         * 
         * @throws Exception            if any error occurs during the seeding process
         */
        @Override
        public void run(String... args) throws Exception {
                User bob = userRepository.findByEmail("bob.doe@example.com")
                                .orElseThrow(() -> new EntityNotFoundException("User not found: bob.doe@example.com"));
                tailorRepository.save(new Tailor(bob, 5));

                User alice = userRepository.findByEmail("alice.doe@example.com")
                                .orElseThrow(() -> new EntityNotFoundException(
                                                "User not found: alice.doe@example.com"));
                tailorRepository.save(new Tailor(alice, 3));
        }
}
