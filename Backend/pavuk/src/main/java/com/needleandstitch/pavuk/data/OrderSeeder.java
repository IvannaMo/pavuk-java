package com.needleandstitch.pavuk.data;

import java.util.List;

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

/**
 * Seeder class for initializing default orders in the database. 
 * This class checks that certain orders are present in the database and adds them if they are missing.
 *
 * <p>The seeding process is triggered during application startup.</p>
 *
 *
 * @author                          Needle &amp; Stitch
 * @version                         1.0.0
 * @since                           15.12.2024
 */
@Component
@Order(13)
public class OrderSeeder implements CommandLineRunner {
    /** 
    * Repository for performing CRUD operations on orders. 
    */
    private final OrderRepository orderRepository;

    /** 
    * Repository for performing CRUD operations on customers. 
    */
    private final CustomerRepository customerRepository;

    /** 
    * Repository for performing CRUD operations on clothing items. 
    */
    private final ClothingItemRepository clothingItemRepository;

    /** 
    * Repository for performing CRUD operations on shippingInfo. 
    */
    private final ShippingInfoRepository shippingInfoRepository;

    
     /**
     * Constructs a new OrderSeeder with the specified repository.
     *
     * @param orderRepository 	     The repository used for interacting with the Order entities
     * @param customerRepository 	 The repository used for interacting with the Customer entities
     * @param clothingItemRepository The repository used for interacting with the ClothingItem entities
     * @param shippingInfoRepository The repository used for interacting with the ShippingInfo entities
     */
    public OrderSeeder(OrderRepository orderRepository, CustomerRepository customerRepository, ClothingItemRepository clothingItemRepository, ShippingInfoRepository shippingInfoRepository) {
        this.orderRepository = orderRepository;
        this.customerRepository = customerRepository;
        this.clothingItemRepository = clothingItemRepository;
        this.shippingInfoRepository = shippingInfoRepository;
    }

    /**
     * Seeds the database with default orders if they are not already present.
     *
     * @param args                   The command-line arguments passed to the application
     * 
     * @throws Exception 			 if any error occurs during the seeding process
     */
    @Override
    public void run(String... args) throws Exception {
        Customer customer = customerRepository.findByUserEmail("john.doe@example.com")
                .orElseThrow(() -> new EntityNotFoundException("Customer not found: john.doe@example.com"));

        List<ClothingItem> clothingItems = clothingItemRepository.findByName("Футболка");
        if (clothingItems.isEmpty()) {
            throw new EntityNotFoundException("ClothingItems not found: Футболка");
        }
        
        ShippingInfo shippingInfo = shippingInfoRepository.findByPostalCode("65001")
                .orElseThrow(() -> new EntityNotFoundException("ShippingInfo not found: 65001"));

        for (ClothingItem clothingItem : clothingItems) {
            com.needleandstitch.pavuk.model.Order order = new com.needleandstitch.pavuk.model.Order(customer, clothingItem, shippingInfo);
            orderRepository.save(order);
        }
    }
}
