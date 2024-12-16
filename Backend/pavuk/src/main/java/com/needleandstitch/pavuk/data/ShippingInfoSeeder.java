package com.needleandstitch.pavuk.data;

import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import com.needleandstitch.pavuk.model.PostService;
import com.needleandstitch.pavuk.model.ShippingInfo;
import com.needleandstitch.pavuk.repository.PostServiceRepository;
import com.needleandstitch.pavuk.repository.ShippingInfoRepository;
import jakarta.persistence.EntityNotFoundException;

/**
 * Seeder class for initializing default shipping infos in the database. 
 * This class checks that certain shipping infos are present in the database and adds them if they are missing.
 *
 * <p>The seeding process is triggered during application startup.</p>
 *
 *
 * @author                           Needle & Stitch
 * @version                          1.0.0
 * @since                            15.12.2024
 */
@Component
@Order(12)
public class ShippingInfoSeeder implements CommandLineRunner {
    /** 
    * Repository for performing CRUD operations on shipping infos. 
    */
    private final ShippingInfoRepository shippingInfoRepository;

    /** 
    * Repository for performing CRUD operations on post services. 
    */
    private final PostServiceRepository postServiceRepository;

    /**
     * Constructs a new ShippingInfoSeeder with the specified repository.
     *
     * @param shippingInfoRepository The repository used for interacting with the ShippingInfo entities
	 * @param postServiceRepository  The repository used for interacting with the PostService entities
     */
    public ShippingInfoSeeder(ShippingInfoRepository shippingInfoRepository, PostServiceRepository postServiceRepository) {
        this.shippingInfoRepository = shippingInfoRepository;
        this.postServiceRepository = postServiceRepository;
    }

     /**
     * Seeds the database with default shipping infos if they are not already present.
     *
     * @param args                   The command-line arguments passed to the application
     * 
     * @throws Exception 			 if any error occurs during the seeding process
     */
    @Override
    public void run(String... args) throws Exception {
        PostService novaPoshtaPostService = postServiceRepository.findByName("Нова пошта")
                .orElseThrow(() -> new EntityNotFoundException("PostService not found: Нова пошта"));
        PostService ukrPoshtaPostService = postServiceRepository.findByName("Укрпошта")
                .orElseThrow(() -> new EntityNotFoundException("PostService not found: Укрпошта"));

        if (shippingInfoRepository.findByPostalCode("65001").isEmpty()) {
            shippingInfoRepository.save(new ShippingInfo("Україна", "Одеська область", "Одеса", "65001", novaPoshtaPostService));
        }
        if (shippingInfoRepository.findByPostalCode("01001").isEmpty()) {
            shippingInfoRepository.save(new ShippingInfo("Україна", "Київська область", "Київ", "01001", ukrPoshtaPostService));
        }
    }
}
