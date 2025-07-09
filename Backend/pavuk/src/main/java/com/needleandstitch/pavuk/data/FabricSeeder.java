package com.needleandstitch.pavuk.data;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import com.needleandstitch.pavuk.model.Color;
import com.needleandstitch.pavuk.model.Fabric;
import com.needleandstitch.pavuk.model.Image;
import com.needleandstitch.pavuk.repository.ColorRepository;
import com.needleandstitch.pavuk.repository.FabricRepository;
import com.needleandstitch.pavuk.repository.ImageRepository;
import jakarta.persistence.EntityNotFoundException;

/**
 * Seeder class for initializing default fabrics in the database. 
 * This class checks that certain fabrics are present in the database and adds them if they are missing.
 *
 * <p>The seeding process is triggered during application startup.</p>
 *
 *
 * @author                          Needle &amp; Stitch
 * @version                         1.0.0
 * @since                           15.12.2024
 */
@Component
@Order(9)
public class FabricSeeder implements CommandLineRunner {
    /** 
    * Repository for performing CRUD operations on fabrics. 
    */
	private final FabricRepository fabricRepository;

    /** 
    * Repository for performing CRUD operations on images. 
    */
	private final ImageRepository imageRepository;

    /** 
    * Repository for performing CRUD operations on colors. 
    */
	private final ColorRepository colorRepository;

    /**
     * Constructs a new FabricSeeder with the specified repository.
     *
     * @param fabricRepository 	    The repository used for interacting with the Fabric entities
     * @param imageRepository 	    The repository used for interacting with the Image entities
     * @param colorRepository 	    The repository used for interacting with the Color entities
     */
    public FabricSeeder(FabricRepository fabricRepository, ImageRepository imageRepository, ColorRepository colorRepository) {
        this.fabricRepository = fabricRepository;
        this.imageRepository = imageRepository;
        this.colorRepository = colorRepository;
    }
    
    /**
     * Seeds the database with default fabrics if they are not already present.
     *
     * @param args                   The command-line arguments passed to the application
     * 
     * @throws Exception 			 if any error occurs during the seeding process
     */
    @Override
    public void run(String... args) throws Exception {
    	List<String> colorHexValues = Arrays.asList(
    	        "#f9f9f9", "#e6e7e8", "#d3d5d7", "#97c682", "#7dc060", 
    	        "#505050", "#3d3d3d", "#202221", "#d15050", "#d5bdf3", 
    	        "#b4e0f8", "#ffe9a0", "#ffdd5c", "#ffaa4e", "#ffb3b3", 
    	        "#ffcd82", "#b2ece1", "#6c99f5", "#5e5ed8", "#9959c8", 
    	        "#f87f98", "#91573b", "#533529", "#ede0c8", "#d7c4a4"
    	    );
    	
        Set<Color> fabricColors = new HashSet<>();
        for (String hex : colorHexValues) {
            fabricColors.add(
                colorRepository.findByHex(hex)
                    .orElseThrow(() -> new EntityNotFoundException("Color not found: " + hex))
            );
        }
        
        Image fabric1Image = imageRepository.findByName("fabric1")
                .orElseThrow(() -> new EntityNotFoundException("Image not found: fabric1"));
        Image fabric2Image = imageRepository.findByName("fabric2")
                .orElseThrow(() -> new EntityNotFoundException("Image not found: fabric2"));
        
    	if (fabricRepository.findByName("fabric1").isEmpty()) {
    		fabricRepository.save(new Fabric("fabric1", fabric1Image, fabricColors));
    	}
    	if (fabricRepository.findByName("fabric2").isEmpty()) {
    		fabricRepository.save(new Fabric("fabric2", fabric2Image, fabricColors));
    	}
    }
}
