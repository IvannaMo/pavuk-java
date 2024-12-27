package com.needleandstitch.pavuk.service;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityNotFoundException;
import jakarta.persistence.PersistenceContext;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.needleandstitch.pavuk.model.ClothingItem;
import com.needleandstitch.pavuk.model.Customer;
import com.needleandstitch.pavuk.model.Order;
import com.needleandstitch.pavuk.model.ShippingInfo;
import com.needleandstitch.pavuk.model.User;
import com.needleandstitch.pavuk.repository.CustomerRepository;
import com.needleandstitch.pavuk.repository.OrderRepository;
import com.needleandstitch.pavuk.repository.ShippingInfoRepository;


@Service
public class OrderService {
    @Autowired
    private OrderRepository orderRepository;
    
    @Autowired
    private CustomerRepository customerRepository;
    
    @Autowired
    private ShippingInfoRepository shippingInfoRepository;

    @PersistenceContext
    private EntityManager entityManager;

    
    @Transactional(readOnly = true)
    public Order findById(Long id) {
        return orderRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Order not found: " + id));
    }
    
    @Transactional(readOnly = true)
    public List<Order> findAll() {
        return orderRepository.findAll();
    }

    @Transactional
    public Order createOrder(User user, ClothingItem clothingItem, ShippingInfo shippingInfo) {
    	Customer customer = customerRepository
        		.findByUserEmail(user.getEmail())
        		.orElseGet(() -> customerRepository.save(new Customer(user)));
    	
    	ShippingInfo persistentShippingInfo = shippingInfoRepository
    		.findByPostalCode(shippingInfo.getPostalCode())
    		.orElseGet(() -> shippingInfoRepository.save(shippingInfo));
    	
    	Order newOrder = new Order(customer, clothingItem, persistentShippingInfo);
        return orderRepository.save(newOrder);
    }
}
