package com.needleandstitch.pavuk.service;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityNotFoundException;
import jakarta.persistence.PersistenceContext;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.needleandstitch.pavuk.model.ClothingItem;
import com.needleandstitch.pavuk.repository.ClothingItemRepository;


@Service
public class ClothingItemService {
    @Autowired
    private ClothingItemRepository clothingItemRepository;

    @PersistenceContext
    private EntityManager entityManager;

    @Transactional(readOnly = true)
    public ClothingItem findById(Long id) {
        return clothingItemRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("ClothingItem not found: " + id));
    }

    @Transactional(readOnly = true)
    public List<ClothingItem> findAll() {
        return clothingItemRepository.findAll();
    }
}
