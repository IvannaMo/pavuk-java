package com.needleandstitch.pavuk.model;

import java.util.Set;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

/**
 * An entity class for clothing item category.
 * <p>
 * This class serves as a model for the category table in the database.
 * </p> 
 * 
 * @author                            Needle &amp; Stitch
 * @version                           1.0.0
 * @since                             15.12.2024
 */
@Entity
@Table(name = "categories")
public class Category {
	 /**
     * The unique identifier for the category.
     * <p>
     * Serves as the primary key for the "categories" table, auto-generated.
     * </p>
     */
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

	 /**
     * The unique name of the category.
     * <p>
     * This field is mandatory, has a maximum length of 50 characters, and must be unique.
     * </p>
     */
	@Column(length = 50, nullable = false, unique = true)
	private String name;
	
	 /**
     * The set of clothing items associated with this category.
     * <p>
     * It is a one-to-many relationship where one category can have multiple ClothingItem objects.
     * </p>
     */
	@OneToMany(mappedBy = "category")
    private Set<ClothingItem> clothingItems;
	
	 /**
     * Default constructor.
     */
	public Category() {}

	 /**
     * Constructor for creating a category with a name.
     * 
     * @param name 					The name of the category
     */
	public Category(String name) {
		this.name = name;
	}
	
	  /**
     * Gets the unique identifier of the category.
     * 
     * @return 						The category ID
     */
	public Long getId() {
		return id;
	}

	/**
     * Sets the unique identifier for the category.
     * 
     * @param id 					The category ID to set
     */
	public void setId(Long id) {
		this.id = id;
	}
	
	/**
     * Gets the name of the category.
     * 
     * @return 						The category name
     */
	public String getName() {
		return name;
	}

	 /**
     * Sets the name of the category.
     * 
     * @param name 					The category name to set
     */
	public void setName(String name) {
		this.name = name;
	}
}
