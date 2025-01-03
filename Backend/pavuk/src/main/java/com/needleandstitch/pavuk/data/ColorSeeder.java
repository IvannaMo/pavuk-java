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
    	String[] hexColors = {
    			"#f9f9f9", "#e6e7e8", "#d3d5d7", "#97c682", "#7dc060", 
    	        "#505050", "#3d3d3d", "#202221", "#d15050", "#d5bdf3", 
    	        "#b4e0f8", "#ffe9a0", "#ffdd5c", "#ffaa4e", "#ffb3b3", 
    	        "#ffcd82", "#b2ece1", "#6c99f5", "#5e5ed8", "#9959c8", 
    	        "#f87f98", "#91573b", "#533529", "#ede0c8", "#d7c4a4"
    	    };
    	
    	for (String hex : hexColors) {
            if (colorRepository.findByHex(hex).isEmpty()) {
                colorRepository.save(new Color(hex));
            }
        }
    }
}
