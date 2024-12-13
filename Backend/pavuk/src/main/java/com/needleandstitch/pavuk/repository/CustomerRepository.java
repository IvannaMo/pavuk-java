package com.needleandstitch.pavuk.repository;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import com.needleandstitch.pavuk.model.Customer;


public interface CustomerRepository extends JpaRepository<Customer, Long> {
	Optional<Customer> findByUserEmail(String email);
}
