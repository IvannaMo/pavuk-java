package com.needleandstitch.pavuk.data;

import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import com.needleandstitch.pavuk.model.Customer;
import com.needleandstitch.pavuk.model.User;
import com.needleandstitch.pavuk.repository.CustomerRepository;
import com.needleandstitch.pavuk.repository.UserRepository;
import jakarta.persistence.EntityNotFoundException;


@Component
@Order(4)
public class CustomerSeeder implements CommandLineRunner {
	private final CustomerRepository customerRepository;
	private final UserRepository userRepository;
	 
	public CustomerSeeder(CustomerRepository customerRepository, UserRepository userRepository) {
		this.customerRepository = customerRepository;   
		this.userRepository = userRepository;  
	}
   
	@Override
	public void run(String... args) throws Exception {
		User john = userRepository.findByEmail("john.doe@example.com")
                .orElseThrow(() -> new EntityNotFoundException("User not found: john.doe@example.com"));
		customerRepository.save(new Customer(john));
	        
		User jane = userRepository.findByEmail("jane.doe@example.com")
                .orElseThrow(() -> new EntityNotFoundException("User not found: jane.doe@example.com"));
		customerRepository.save(new Customer(jane));
		
		User kate = userRepository.findByEmail("kate.doe@example.com")
                .orElseThrow(() -> new EntityNotFoundException("User not found: kate.doe@example.com"));
		customerRepository.save(new Customer(kate));
	}
}