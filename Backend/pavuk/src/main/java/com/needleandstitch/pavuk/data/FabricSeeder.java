package com.needleandstitch.pavuk.data;

import java.util.HashSet;
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


@Component
@Order(9)
public class FabricSeeder implements CommandLineRunner {
	private final FabricRepository fabricRepository;
	private final ImageRepository imageRepository;
	private final ColorRepository colorRepository;

    public FabricSeeder(FabricRepository fabricRepository, ImageRepository imageRepository, ColorRepository colorRepository) {
        this.fabricRepository = fabricRepository;
        this.imageRepository = imageRepository;
        this.colorRepository = colorRepository;
    }
    
    @Override
    public void run(String... args) throws Exception {
    	Color color1 = colorRepository.findByHex("#f9f9f9")
                .orElseThrow(() -> new EntityNotFoundException("Color not found: #f9f9f9"));
    	Color color2 = colorRepository.findByHex("#e6e7e8")
                .orElseThrow(() -> new EntityNotFoundException("Color not found: #e6e7e8"));
    	Color color3 = colorRepository.findByHex("#d3d5d7")
                .orElseThrow(() -> new EntityNotFoundException("Color not found: #d3d5d7"));
    	
        Image fabric1Image = imageRepository.findByName("fabric1")
                .orElseThrow(() -> new EntityNotFoundException("Image not found: fabric1"));
        Image fabric2Image = imageRepository.findByName("fabric2")
                .orElseThrow(() -> new EntityNotFoundException("Image not found: fabric2"));
    			
        Set<Color> fabricColors = new HashSet<>();
        fabricColors.add(color1);
        fabricColors.add(color2);
        fabricColors.add(color3);
        
    	if (fabricRepository.findByName("#fabric1").isEmpty()) {
    		fabricRepository.save(new Fabric("fabric1", fabric1Image, fabricColors));
    	}
    	if (fabricRepository.findByName("#fabric2").isEmpty()) {
    		fabricRepository.save(new Fabric("fabric2", fabric2Image, fabricColors));
    	}
    }
}
