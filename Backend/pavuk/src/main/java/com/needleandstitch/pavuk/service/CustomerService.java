package com.needleandstitch.pavuk.service;

import jakarta.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.needleandstitch.pavuk.model.Customer;
import com.needleandstitch.pavuk.repository.CustomerRepository;

@Service
public class CustomerService {

    @Autowired
    private CustomerRepository customerRepository;

    @Transactional(readOnly = true)
    public Customer getCustomerIdByUserId(Long userId) {
        return customerRepository.findByUserId(userId)
                .orElseThrow(() -> new EntityNotFoundException("Customer not found for userId: " + userId));
    }
}



