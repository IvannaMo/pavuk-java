package com.needleandstitch.pavuk.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.needleandstitch.pavuk.model.Customer;
import com.needleandstitch.pavuk.service.CustomerService;

@Controller
@RequestMapping("/customers")
@CrossOrigin
public class CustomerController {

    @Autowired
    private CustomerService customerService;

    @GetMapping("/user/{userId}")
    public ResponseEntity<Customer> getCustomerIdByUserId(@PathVariable Long userId) {
        Customer customer = customerService.getCustomerIdByUserId(userId);
        return ResponseEntity.ok(customer);
    }
}