package com.needleandstitch.pavuk.data;

import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import com.needleandstitch.pavuk.model.Color;
import com.needleandstitch.pavuk.repository.ColorRepository;


@Component
@Order(8)
public class ColorSeeder implements CommandLineRunner {
	private final ColorRepository colorRepository;

    public ColorSeeder(ColorRepository colorRepository) {
        this.colorRepository = colorRepository;
    }
    
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
