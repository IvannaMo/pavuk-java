package com.needleandstitch.pavuk.data;

import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import com.needleandstitch.pavuk.model.Tailor;
import com.needleandstitch.pavuk.model.User;
import com.needleandstitch.pavuk.repository.TailorRepository;
import com.needleandstitch.pavuk.repository.UserRepository;


@Component
@Order(3)
public class TailorSeeder implements CommandLineRunner {
	private final TailorRepository tailorRepository;
	private final UserRepository userRepository;
	 
	public TailorSeeder(TailorRepository tailorRepository, UserRepository userRepository) {
		this.tailorRepository = tailorRepository;   
		this.userRepository = userRepository;  
	}
   
	@Override
	public void run(String... args) throws Exception {
		User bob = userRepository.findByEmail("bob.doe@example.com");
		if (bob != null) {
			tailorRepository.save(new Tailor(bob, 5));
		}
	        
		User alice = userRepository.findByEmail("alice.doe@example.com");
	    if (alice != null) {
	    	tailorRepository.save(new Tailor(alice, 3));
	    }
	}
}
