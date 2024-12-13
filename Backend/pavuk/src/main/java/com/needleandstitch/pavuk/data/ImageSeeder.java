package com.needleandstitch.pavuk.data;

import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import com.needleandstitch.pavuk.model.Image;
import com.needleandstitch.pavuk.repository.ImageRepository;


@Component
@Order(6)
public class ImageSeeder implements CommandLineRunner {
	private final ImageRepository imageRepository;

    public ImageSeeder(ImageRepository imageRepository) {
        this.imageRepository = imageRepository;
    }

    @Override
    public void run(String... args) throws Exception {
    	String tShirtUrl = "/images/basic-clothing/t-shirt.svg";
    	
        if (imageRepository.findByName("t-shirt").isEmpty()) {
        	Image image = new Image("t-shirt", tShirtUrl, true);
            imageRepository.save(image);
        }
        
        String fabric1Url = "/images/fabrics/fabric1.jpg";
        String fabric2Url = "/images/fabrics/fabric2.jpg";
    	
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
