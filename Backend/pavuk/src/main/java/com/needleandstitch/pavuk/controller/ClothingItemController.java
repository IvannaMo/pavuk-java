package com.needleandstitch.pavuk.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.needleandstitch.pavuk.model.ClothingItem;
import com.needleandstitch.pavuk.service.ClothingItemService;

import jakarta.persistence.EntityNotFoundException;


@Controller
@RequestMapping("/clothing-items")
@CrossOrigin
public class ClothingItemController {
	@Autowired
    private ClothingItemService clothingItemService;

    @GetMapping
    public ResponseEntity<List<ClothingItem>> getAllClothingItems() {
        List<ClothingItem> clothingItems = clothingItemService.findAll();
        return ResponseEntity.ok(clothingItems);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ClothingItem> getClothingItemById(@PathVariable Long id) {
        try {
        	ClothingItem clothingItem = clothingItemService.findById(id);
            return ResponseEntity.ok(clothingItem);
        } catch (EntityNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }
}
