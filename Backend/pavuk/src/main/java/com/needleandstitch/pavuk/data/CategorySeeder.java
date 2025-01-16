package com.needleandstitch.pavuk.data;

import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import com.needleandstitch.pavuk.model.Category;
import com.needleandstitch.pavuk.repository.CategoryRepository;


/**
 * Seeder class for initializing default categories in the database. 
 * This class checks that certain categories are present in the database and adds them if they are missing.
 *
 * <p>The seeding process is triggered during application startup.</p>
 *
 *
 * @author                          Needle &amp; Stitch
 * @version                         1.0.0
 * @since                           15.12.2024
 */
@Component
@Order(5)
public class CategorySeeder implements CommandLineRunner {
    /** 
    * Repository for performing CRUD operations on categories. 
    */
	private final CategoryRepository categoryRepository;

     /**
     * Constructs a new CategorySeeder with the specified repository.
     *
     * @param categoryRepository    The repository used for interacting with the Category entities
     */
    public CategorySeeder(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    /**
     * Seeds the database with default categories if they are not already present.
     *
     * @param args                  The command-line arguments passed to the application
     * 
     * @throws Exception            if any error occurs during the seeding process
     */
    @Override
    public void run(String... args) throws Exception {
    	if (categoryRepository.findByName("Футболки").isEmpty()) {
    		categoryRepository.save(new Category("Футболки"));
    	}
    	if (categoryRepository.findByName("Светри").isEmpty()) {
    		categoryRepository.save(new Category("Светри"));
    	}
    }
}
