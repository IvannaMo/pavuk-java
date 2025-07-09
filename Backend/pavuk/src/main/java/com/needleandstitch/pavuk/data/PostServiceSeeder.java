package com.needleandstitch.pavuk.data;

import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import com.needleandstitch.pavuk.model.PostService;
import com.needleandstitch.pavuk.repository.PostServiceRepository;

/**
 * Seeder class for initializing default post services in the database.
 * This class checks that certain post services are present in the database and adds them if they are missing.
 *
 * <p>
 * The seeding process is triggered during application startup.
 * </p>
 *
 *
 * @author                          Needle &amp; Stitch
 * @version                         1.0.0
 * @since                           15.12.2024
 */
@Component
@Order(11)
public class PostServiceSeeder implements CommandLineRunner {
    /**
     * Repository for performing CRUD operations on post services.
     */
    private final PostServiceRepository postServiceRepository;

    /**
     * Constructs a new PostServiceSeeder with the specified repository.
     *
     * @param postServiceRepository The repository used for interacting with the PostService entities
     */
    public PostServiceSeeder(PostServiceRepository postServiceRepository) {
        this.postServiceRepository = postServiceRepository;
    }

    /**
     * Seeds the database with default post services if they are not already
     * present.
     *
     * @param args                  The command-line arguments passed to the application
     * 
     * @throws Exception            if any error occurs during the seeding process
     */
    @Override
    public void run(String... args) throws Exception {
        if (postServiceRepository.findByName("Нова пошта").isEmpty()) {
            postServiceRepository.save(new PostService("Нова пошта"));
        }
        if (postServiceRepository.findByName("Укрпошта").isEmpty()) {
            postServiceRepository.save(new PostService("Укрпошта"));
        }
    }
}
