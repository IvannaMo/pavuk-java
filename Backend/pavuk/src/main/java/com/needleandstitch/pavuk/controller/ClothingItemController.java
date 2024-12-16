package com.needleandstitch.pavuk.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.needleandstitch.pavuk.model.ClothingItem;
import com.needleandstitch.pavuk.service.ClothingItemService;

import jakarta.persistence.EntityNotFoundException;

/**
 * REST controller for managing ClothingItem objects.
 * <p>
 * This class provides endpoints for handling clothing item-related operations such as
 * retrieving clothing items, creating new items, updating existing ones, and deleting them.
 * </p>
 * 
 * <h2>Endpoints:</h2>
 * <ul>
 *   <li>GET /clothing-items          Retrieve all roles</li>
 *   <li>GET /clothing-items/{id}     Retrieve a role by its ID</li>
 *   <li>POST /clothing-items         Create a new role</li>
 *   <li>PUT /clothing-items/{id}     Update an existing role</li>
 *   <li>DELETE /clothing-items/{id}  Delete a role</li>
 * </ul>
 * 
 * @author                            Needle &amp; Stitch
 * @version                           1.0.0
 * @since                             15.12.2024
 */
@Controller
@RequestMapping("/clothing-items")
@CrossOrigin
public class ClothingItemController {
     /**
     * Service class for handling clothing item-related operations.
     */
	@Autowired
    private ClothingItemService clothingItemService;
    
    /**
     * 
     * Retrieves all clothing items.
     *
     * @return                 A ResponseEntity containing a list of all ClothingItem objects.
     * 
     */
    @GetMapping
    public ResponseEntity<List<ClothingItem>> getAllClothingItems() {
        List<ClothingItem> clothingItems = clothingItemService.findAll();
        return ResponseEntity.ok(clothingItems);
    }

    /**
     * 
     * Endpoint to retrieve a clothing item by its ID.
     *
     * @param id             The ID of the clothing item.
     * @return               A ResponseEntity containing the ClothingItem object if found or a 404 Not Found status if the object does not exist.
     * 
     */
    @GetMapping("/{id}")
    public ResponseEntity<ClothingItem> getClothingItemById(@PathVariable Long id) {
        try {
        	ClothingItem clothingItem = clothingItemService.findById(id);
            return ResponseEntity.ok(clothingItem);
        } catch (EntityNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    /**
     * Endpoint to create a new clothing item.
     *
     * @param clothingItem   The ClothingItem object to create.
     * @return               A ResponseEntity with a 201 Created status if successful.
     * 
     */
    @PostMapping
    public ResponseEntity<Void> createClothingItem(@RequestBody ClothingItem clothingItem) {
        clothingItemService.createClothingItem(
                clothingItem.getName(),
                clothingItem.getDescription(),
                clothingItem.getPrice(),
                clothingItem.getImages(),
                clothingItem.getCategory()
        );
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    /**
     * Endpoint to update an existing clothing item by its ID.
     *
     * @param id            The ID of the clothing item to update.
     * @param clothingItem  The updated ClothingItem object.
     * @return              A ResponseEntity with a 200 OK status if successful, or a 404 Not Found status if the item does not exist.
     * 
     */
    @PutMapping("/{id}")
    public ResponseEntity<ClothingItem> updateClothingItem(@PathVariable Long id, @RequestBody ClothingItem clothingItem) {
        try {
            clothingItemService.updateClothingItem(id, clothingItem.getName(), clothingItem.getDescription(), clothingItem.getPrice(), clothingItem.getImages(), clothingItem.getCategory());
            return ResponseEntity.ok().build();
        } catch (EntityNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    /**
     * Endpoint to delete a clothing item by its ID.
     *
     * @param id            The ID of the clothing item to delete.
     * @return              A ResponseEntity with a 204 No Content status if successful, or a 404 Not Found status if not.
     * 
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteClothingItem(@PathVariable Long id) {
        try {
            clothingItemService.deleteClothingItem(id);
            return ResponseEntity.noContent().build();
        } catch (EntityNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }
}
