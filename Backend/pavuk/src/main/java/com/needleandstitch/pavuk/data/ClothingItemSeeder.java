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
import com.needleandstitch.pavuk.repository.CategoryRepository;
import com.needleandstitch.pavuk.repository.ClothingItemRepository;
import com.needleandstitch.pavuk.repository.ImageRepository;
import jakarta.persistence.EntityNotFoundException;


@Component
@Order(10)
public class ClothingItemSeeder implements CommandLineRunner {
	private final ClothingItemRepository clothingItemRepository;
	private final ImageRepository imageRepository;
    private final CategoryRepository categoryRepository;

    public ClothingItemSeeder(ClothingItemRepository clothingItemRepository, ImageRepository imageRepository, CategoryRepository categoryRepository) {
        this.clothingItemRepository = clothingItemRepository;
        this.imageRepository = imageRepository;
        this.categoryRepository = categoryRepository;
    }
    
    @Override
    public void run(String... args) throws Exception {
        Category tShirtCategory = categoryRepository.findByName("Футболки")
                .orElseThrow(() -> new EntityNotFoundException("Category not found: Футболки"));

        Image tShirtImage = imageRepository.findByName("t-shirt")
                .orElseThrow(() -> new EntityNotFoundException("Image not found: t-shirt"));

        Set<Image> tShirtImages = new HashSet<>();
        tShirtImages.add(tShirtImage);

        if (clothingItemRepository.findByName("Футболка").isEmpty()) {
            clothingItemRepository.save(new ClothingItem("Футболка", "Lorem ipsum dolor sit amet, consectetur adipiscing elit.", BigDecimal.valueOf(500), tShirtImages, tShirtCategory));
        }
    }
}
