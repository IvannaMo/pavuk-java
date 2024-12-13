package com.needleandstitch.pavuk.data;

import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import com.needleandstitch.pavuk.model.Category;
import com.needleandstitch.pavuk.repository.CategoryRepository;


@Component
@Order(5)
public class CategorySeeder implements CommandLineRunner {
	private final CategoryRepository categoryRepository;

    public CategorySeeder(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }
    
    @Override
    public void run(String... args) throws Exception {
    	if (categoryRepository.findByName("Футболки").isEmpty()) {
    		categoryRepository.save(new Category("Футболки"));
    	}
    }
}
