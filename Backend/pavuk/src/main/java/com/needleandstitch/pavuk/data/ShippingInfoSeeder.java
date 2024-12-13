package com.needleandstitch.pavuk.data;

import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import com.needleandstitch.pavuk.model.PostService;
import com.needleandstitch.pavuk.model.ShippingInfo;
import com.needleandstitch.pavuk.repository.PostServiceRepository;
import com.needleandstitch.pavuk.repository.ShippingInfoRepository;
import jakarta.persistence.EntityNotFoundException;


@Component
@Order(12)
public class ShippingInfoSeeder implements CommandLineRunner {
    private final ShippingInfoRepository shippingInfoRepository;
    private final PostServiceRepository postServiceRepository;

    public ShippingInfoSeeder(ShippingInfoRepository shippingInfoRepository, PostServiceRepository postServiceRepository) {
        this.shippingInfoRepository = shippingInfoRepository;
        this.postServiceRepository = postServiceRepository;
    }

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
