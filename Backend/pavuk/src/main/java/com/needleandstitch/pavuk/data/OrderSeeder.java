package com.needleandstitch.pavuk.data;

import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import com.needleandstitch.pavuk.model.ClothingItem;
import com.needleandstitch.pavuk.model.Customer;
import com.needleandstitch.pavuk.model.ShippingInfo;
import com.needleandstitch.pavuk.repository.ClothingItemRepository;
import com.needleandstitch.pavuk.repository.CustomerRepository;
import com.needleandstitch.pavuk.repository.OrderRepository;
import com.needleandstitch.pavuk.repository.ShippingInfoRepository;
import jakarta.persistence.EntityNotFoundException;


@Component
@Order(13)
public class OrderSeeder implements CommandLineRunner {
    private final OrderRepository orderRepository;
    private final CustomerRepository customerRepository;
    private final ClothingItemRepository clothingItemRepository;
    private final ShippingInfoRepository shippingInfoRepository;

    public OrderSeeder(OrderRepository orderRepository, CustomerRepository customerRepository, ClothingItemRepository clothingItemRepository, ShippingInfoRepository shippingInfoRepository) {
        this.orderRepository = orderRepository;
        this.customerRepository = customerRepository;
        this.clothingItemRepository = clothingItemRepository;
        this.shippingInfoRepository = shippingInfoRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        Customer customer = customerRepository.findByUserEmail("john.doe@example.com")
                .orElseThrow(() -> new EntityNotFoundException("Customer not found: john.doe@example.com"));

        ClothingItem clothingItem = clothingItemRepository.findByName("Футболка")
                .orElseThrow(() -> new EntityNotFoundException("ClothingItem not found: Футболка"));

        ShippingInfo shippingInfo = shippingInfoRepository.findByPostalCode("65001")
                .orElseThrow(() -> new EntityNotFoundException("ShippingInfo not found: 65001"));

        com.needleandstitch.pavuk.model.Order order = new com.needleandstitch.pavuk.model.Order(customer, clothingItem, shippingInfo);
        orderRepository.save(order);
    }
}
