package com.needleandstitch.pavuk.data;

import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import com.needleandstitch.pavuk.model.Color;
import com.needleandstitch.pavuk.repository.ColorRepository;

/**
 * Seeder class for initializing default colors in the database. 
 * This class checks that certain colors are present in the database and adds them if they are missing.
 *
 * <p>The seeding process is triggered during application startup.</p>
 *
 *
 * @author                          Needle &amp; Stitch
 * @version                         1.0.0
 * @since                           15.12.2024
 */
@Component
@Order(8)
public class ColorSeeder implements CommandLineRunner {
	/** 
    * Repository for performing CRUD operations on colors. 
    */
	private final ColorRepository colorRepository;

	 /**
     * Constructs a new ColorSeeder with the specified repository.
     *
     * @param colorRepository 		The repository used for interacting with the Color entities
     */
    public ColorSeeder(ColorRepository colorRepository) {
        this.colorRepository = colorRepository;
    }
    
	/**
     * Seeds the database with default colors if they are not already present.
     *
     * @param args                   The command-line arguments passed to the application
     * 
     * @throws Exception 			 if any error occurs during the seeding process
     */
    @Override
    public void run(String... args) throws Exception {
    	if (colorRepository.findByHex("#f9f9f9").isEmpty()) {
    		colorRepository.save(new Color("#f9f9f9"));
    	}
    	if (colorRepository.findByHex("#e6e7e8").isEmpty()) {
    		colorRepository.save(new Color("#e6e7e8"));
    	}
    	if (colorRepository.findByHex("#d3d5d7").isEmpty()) {
    		colorRepository.save(new Color("#d3d5d7"));
    	}
    	
    	if (colorRepository.findByHex("#97c682").isEmpty()) {
    		colorRepository.save(new Color("#97c682"));
    	}
    	if (colorRepository.findByHex("#648a61").isEmpty()) {
    		colorRepository.save(new Color("#648a61"));
    	}
    	if (colorRepository.findByHex("#445a57").isEmpty()) {
    		colorRepository.save(new Color("#445a57"));
    	}
    	
    	if (colorRepository.findByHex("#505050").isEmpty()) {
    		colorRepository.save(new Color("#505050"));
    	}
    	if (colorRepository.findByHex("#3d3d3d").isEmpty()) {
    		colorRepository.save(new Color("#3d3d3d"));
    	}
    	if (colorRepository.findByHex("#202221").isEmpty()) {
    		colorRepository.save(new Color("#202221"));
    	}
    }
}
