package com.needleandstitch.pavuk.service;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityNotFoundException;
import jakarta.persistence.PersistenceContext;

import java.math.BigDecimal;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.needleandstitch.pavuk.model.Category;
import com.needleandstitch.pavuk.model.ClothingItem;
import com.needleandstitch.pavuk.model.Image;
import com.needleandstitch.pavuk.repository.ClothingItemRepository;

/**
 * Service layer for managing clothing items.
 * <p>
 * This class provides business logic for operations related to clothing items, 
 * such as retrieving, creating, updating, and deleting clothing items.
 * </p>
 * 
 * <h2>Usage:</h2>
 * <ul>
 *   <li>Retrieve clothing items by ID</li>
 *   <li>Create new clothing items</li>
 *   <li>Update existing clothing items</li>
 *   <li>Delete clothing items</li>
 * </ul>

 * 
 * @author                     Needle &amp; Stitch
 * @version                    1.0.0
 * @since                      15.12.2024
 */
@Service
public class ClothingItemService {
    /**
     * Repository class for performing CRUD operations on clothing items.
     */
    @Autowired
    private ClothingItemRepository clothingItemRepository;

     /**
     * EntityManager used for interacting with the persistence context.
     */
    @PersistenceContext
    private EntityManager entityManager;

     /**
     * Retrieves a clothing item by its ID.
     * 
     * @param id                  The ID of the clothing item to retrieve
     * @return                    The clothing item with the specified ID
     * 
     * @throws EntityNotFoundException if the clothing item is not found
     */
    @Transactional(readOnly = true)
    public ClothingItem findById(Long id) {
        return clothingItemRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("ClothingItem not found: " + id));
    }

    /**
     * Retrieves a list of all clothing items.
     * 
     * @return a list of all clothing items
     */
    @Transactional(readOnly = true)
    public List<ClothingItem> findAll() {
        return clothingItemRepository.findAll();
    }

     /**
     * Creates a new clothing item.
     * 
     * @param name              The name of the clothing item
     * @param description       A description of the clothing item
     * @param price             The price of the clothing item
     * @param images            A set of images associated with the clothing item
     * @param category          The category to which the clothing item belongs
     * @return                  The created clothing item
     */
    @Transactional
    public ClothingItem createClothingItem(String name, String description, BigDecimal price, Set<Image> images, Category category) {
        ClothingItem clothingItem = new ClothingItem(name, description, price, images, category);

        return clothingItemRepository.save(clothingItem);
    }

     /**
     * Updates an existing clothing item.
     * 
     * @param id                The ID of the clothing item to update
     * @param name              The new name for the clothing item
     * @param description       The new description for the clothing item
     * @param price             The new price for the clothing item
     * @param images            The new set of images for the clothing item
     * @param category          The new category for the clothing item
     * @return                  The updated clothing item
     */
    @Transactional
    public ClothingItem updateClothingItem(Long id, String name, String description, BigDecimal price, Set<Image> images, Category category) {
        ClothingItem clothingItem = findById(id);
    
        clothingItem.setName(name);
        clothingItem.setDescription(description);
        clothingItem.setPrice(price);
        clothingItem.setImages(images);
        clothingItem.setCategory(category);
    
        return clothingItemRepository.save(clothingItem);
    }

    /**
     * Deletes a clothing item by its ID.
     * 
     * @param id                The ID of the clothing item to delete
     * 
     * @throws EntityNotFoundException if the clothing item with the specified ID does not exist
     */
    @Transactional
    public void deleteClothingItem(Long id) {
        if (!clothingItemRepository.existsById(id)) {
            throw new EntityNotFoundException("ClothingItem not found: " + id);
        }
        clothingItemRepository.deleteById(id);
    }
}
