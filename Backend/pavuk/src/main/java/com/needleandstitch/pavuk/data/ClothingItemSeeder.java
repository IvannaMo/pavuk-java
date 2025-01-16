package com.needleandstitch.pavuk.data;

import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import com.needleandstitch.pavuk.model.Category;
import com.needleandstitch.pavuk.model.ClothingItem;
import com.needleandstitch.pavuk.model.Image;
import com.needleandstitch.pavuk.model.ClothingItem.Status;
import com.needleandstitch.pavuk.repository.CategoryRepository;
import com.needleandstitch.pavuk.repository.ClothingItemRepository;
import com.needleandstitch.pavuk.repository.ImageRepository;
import jakarta.persistence.EntityNotFoundException;

/**
 * Seeder class for initializing default clothing items in the database. 
 * This class checks that certain clothing items are present in the database and adds them if they are missing.
 *
 * <p>The seeding process is triggered during application startup.</p>
 *
 *
 * @author                           Needle &amp; Stitch
 * @version                          1.0.0
 * @since                            15.12.2024
 */
@Component
@Order(10)
public class ClothingItemSeeder implements CommandLineRunner {
    /** 
    * Repository for performing CRUD operations on clothing items. 
    */
	private final ClothingItemRepository clothingItemRepository;

    /** 
    * Repository for performing CRUD operations on images. 
    */
	private final ImageRepository imageRepository;

    /** 
    * Repository for performing CRUD operations on categories. 
    */
    private final CategoryRepository categoryRepository;

     /**
     * Constructs a new ClothingItemSeeder with the specified repository.
     *
     * @param clothingItemRepository The repository used for interacting with the ClothingItem entities
     * @param imageRepository        The repository used for interacting with the Image entities
     * @param categoryRepository     The repository used for interacting with the Category entities
     */
    public ClothingItemSeeder(ClothingItemRepository clothingItemRepository, ImageRepository imageRepository, CategoryRepository categoryRepository) {
        this.clothingItemRepository = clothingItemRepository;
        this.imageRepository = imageRepository;
        this.categoryRepository = categoryRepository;
    }
    
    /**
     * Seeds the database with default clothing items if they are not already present.
     *
     * @param args                   The command-line arguments passed to the application
     * 
     * @throws Exception             if any error occurs during the seeding process
     */
    @Override
    public void run(String... args) throws Exception {
        Category tShirtCategory = categoryRepository.findByName("Футболки")
                .orElseThrow(() -> new EntityNotFoundException("Category not found: Футболки"));
        Category sweaterCategory = categoryRepository.findByName("Светри")
                .orElseThrow(() -> new EntityNotFoundException("Category not found: Светри"));

        Image tShirtImage = imageRepository.findByName("t-shirt")
                .orElseThrow(() -> new EntityNotFoundException("Image not found: t-shirt"));
        Set<Image> tShirtImages = new HashSet<>();
        tShirtImages.add(tShirtImage);
        
        Image sweaterImage = imageRepository.findByName("sweater")
                .orElseThrow(() -> new EntityNotFoundException("Image not found: sweater"));
        Set<Image> sweaterImages = new HashSet<>();
        sweaterImages.add(sweaterImage);

        if (clothingItemRepository.findByName("Футболка").isEmpty()) {
        	ClothingItem tShirt = new ClothingItem("Футболка", "Lorem ipsum dolor sit amet, consectetur adipiscing elit.", BigDecimal.valueOf(500), tShirtImages, tShirtCategory);
        	tShirt.setStatus(Status.ACTIVE);
        	clothingItemRepository.save(tShirt);
        }
        if (clothingItemRepository.findByName("Светр").isEmpty()) {
        	ClothingItem sweater = new ClothingItem("Светр", "Lorem ipsum dolor sit amet, consectetur adipiscing elit.", BigDecimal.valueOf(750), sweaterImages, sweaterCategory);
        	sweater.setStatus(Status.ACTIVE);
        	clothingItemRepository.save(sweater);
        }
    }
}
