package com.needleandstitch.pavuk.data;

import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import com.needleandstitch.pavuk.model.Image;
import com.needleandstitch.pavuk.repository.ImageRepository;

/**
 * Seeder class for initializing default images in the database. 
 * This class checks that certain images are present in the database and adds them if they are missing.
 *
 * <p>The seeding process is triggered during application startup.</p>
 *
 *
 * @author                          Needle & Stitch
 * @version                         1.0.0
 * @since                           15.12.2024
 */
@Component
@Order(6)
public class ImageSeeder implements CommandLineRunner {
    /** 
    * Repository for performing CRUD operations on images. 
    */
	private final ImageRepository imageRepository;

     /**
     * Constructs a new ImageSeeder with the specified repository.
     *
     * @param imageRepository 	    The repository used for interacting with the Image entities
     */
    public ImageSeeder(ImageRepository imageRepository) {
        this.imageRepository = imageRepository;
    }

     /**
     * Seeds the database with default images if they are not already present.
     *
     * @param args                   The command-line arguments passed to the application
     * 
     * @throws Exception 			 if any error occurs during the seeding process
     */
    @Override
    public void run(String... args) throws Exception {
    	String tShirtUrl = "images/basic-clothing/t-shirt.svg";
    	
        if (imageRepository.findByName("t-shirt").isEmpty()) {
        	Image image = new Image("t-shirt", tShirtUrl, true);
            imageRepository.save(image);
        }
        
        String fabric1Url = "images/fabrics/fabric1.jpg";
        String fabric2Url = "images/fabrics/fabric2.jpg";
    	
        if (imageRepository.findByName("fabric1").isEmpty()) {
        	Image image = new Image("fabric1", fabric1Url, true);
            imageRepository.save(image);
        }
        if (imageRepository.findByName("fabric2").isEmpty()) {
        	Image image = new Image("fabric2", fabric2Url, true);
            imageRepository.save(image);
        }
    }
}
