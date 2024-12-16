package com.needleandstitch.pavuk.data;

import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import com.needleandstitch.pavuk.model.Customer;
import com.needleandstitch.pavuk.model.User;
import com.needleandstitch.pavuk.repository.CustomerRepository;
import com.needleandstitch.pavuk.repository.UserRepository;
import jakarta.persistence.EntityNotFoundException;

/**
 * Seeder class for initializing default customers in the database. 
 * This class checks that certain customers are present in the database and adds them if they are missing.
 *
 * <p>The seeding process is triggered during application startup.</p>
 *
 *
 * @author                          Needle & Stitch
 * @version                         1.0.0
 * @since                           15.12.2024
 */
@Component
@Order(4)
public class CustomerSeeder implements CommandLineRunner {
	/** 
    * Repository for performing CRUD operations on customers. 
    */
	private final CustomerRepository customerRepository;
	/** 
    * Repository for performing CRUD operations on users. 
    */
	private final UserRepository userRepository;
	 
	/**
     * Constructs a new CustomerSeeder with the specified repository.
     *
     * @param customerRepository 	 The repository used for interacting with the Customer entities
     * @param userRepository 	 	 The repository used for interacting with the User entities
     */
	public CustomerSeeder(CustomerRepository customerRepository, UserRepository userRepository) {
		this.customerRepository = customerRepository;   
		this.userRepository = userRepository;  
	}
   
	/**
     * Seeds the database with default customers if they are not already present.
     *
     * @param args                   The command-line arguments passed to the application
     * 
     * @throws Exception 			 if any error occurs during the seeding process
     */
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