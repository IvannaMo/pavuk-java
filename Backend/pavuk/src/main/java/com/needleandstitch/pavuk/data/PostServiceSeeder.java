package com.needleandstitch.pavuk.data;

import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import com.needleandstitch.pavuk.model.PostService;
import com.needleandstitch.pavuk.repository.PostServiceRepository;


@Component
@Order(11)
public class PostServiceSeeder implements CommandLineRunner {
	private final PostServiceRepository postServiceRepository;

    public PostServiceSeeder(PostServiceRepository postServiceRepository) {
        this.postServiceRepository = postServiceRepository;
    }

    @Override
    public void run(String... args) throws Exception {
    	if (postServiceRepository.findByName("Нова пошта").isEmpty()) {
    		postServiceRepository.save(new PostService("Нова пошта"));
    	}
    	if (postServiceRepository.findByName("Укрпошта").isEmpty()) {
    		postServiceRepository.save(new PostService("Укрпошта"));
    	}
    }
}
