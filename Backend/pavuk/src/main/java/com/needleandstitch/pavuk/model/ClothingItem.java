package com.needleandstitch.pavuk.model;

import java.math.BigDecimal;
import java.util.Set;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

/**
 * An entity class for clothing item.
 * <p>
 * This class serves as a model for the clothing item table in the database.
 * </p> 
 * 
 * @author                            Needle & Stitch
 * @version                           1.0.0
 * @since                             15.12.2024
 */
@Entity
@Table(name = "clothing_items")
public class ClothingItem {

    /**
     * The unique identifier for the clothing item.
     * <p>
     * Serves as the primary key for the "clothing_items" table, auto-generated.
     * </p>
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * The name of the clothing item.
     * <p>
     * This field is mandatory, has a maximum length of 100 characters, 
     * and must be unique.
     * </p>
     */
    @Column(length = 100, nullable = false, unique = true)
    private String name;

    /**
     * A description of the clothing item.
     * <p>
     * This field has a maximum length of 500 characters and is optional.
     * </p>
     */
    @Column(length = 500)
    private String description;

    /**
     * The price of the clothing item.
     * <p>
     * This field is mandatory.
     * </p>
     */
    @Column(nullable = false)
    private BigDecimal price;

    /**
     * A set of images associated with the clothing item.
     * <p>
     * This is a many-to-many relationship.
     * </p>
     */
    @OneToMany
    @JoinTable(
        name = "clothing_item_images",
        joinColumns = @JoinColumn(name = "clothing_item_id"),
        inverseJoinColumns = @JoinColumn(name = "image_id")
    )
    private Set<Image> images;

    /**
     * A set of parts that are associated with this clothing item.
     * <p>
     * This is a one-to-many relationship with ClothingItemPart.
     * </p>
     */
    @OneToMany(mappedBy = "clothingItem")
    private Set<ClothingItemPart> parts;

    /**
     * The category of the clothing item.
     * <p>
     * This is a many-to-one relationship with Category.
     * </p>
     */
    @ManyToOne
    @JoinColumn(name = "category_id", referencedColumnName = "id", nullable = false)
    private Category category;

    /**
     * Default constructor.
     */
    public ClothingItem() {}

    /**
     * Constructor for creating a clothing item with specified details.
     * 
     * @param name                  The name of the clothing item
     * @param description           A detailed description of the clothing item
     * @param price                 The price of the clothing item
     * @param images                A set of images associated with the clothing item
     * @param category              The category to which the clothing item belongs
     */
    public ClothingItem(String name, String description, BigDecimal price, Set<Image> images, Category category) {
        this.name = name;
        this.description = description;
        this.price = price;
        this.images = images;
        this.category = category;
    }

    /**
     * Gets the unique identifier of the clothing item.
     * 
     * @return                      The clothing item ID
     */
    public Long getId() {
        return id;
    }

    /**
     * Sets the unique identifier for the clothing item.
     * 
     * @param id                    The clothing item ID to set
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * Gets the name of the clothing item.
     * 
     * @return                      The clothing item name
     */
    public String getName() {
        return name;
    }

    /**
     * Sets the name of the clothing item.
     * 
     * @param name                  The clothing item name to set
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Gets the description of the clothing item.
     * 
     * @return                      The clothing item description
     */
    public String getDescription() {
        return description;
    }

    /**
     * Sets the description of the clothing item.
     * 
     * @param description           The clothing item description to set
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * Gets the price of the clothing item.
     * 
     * @return                      The clothing item price
     */
    public BigDecimal getPrice() {
        return price;
    }

    /**
     * Sets the price of the clothing item.
     * 
     * @param price                 The clothing item price to set
     */
    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    /**
     * Gets the set of images associated with the clothing item.
     * 
     * @return                      The set of images
     */
    public Set<Image> getImages() {
        return images;
    }

    /**
     * Sets the set of images associated with the clothing item.
     * 
     * @param images                The images to associate with the clothing item
     */
    public void setImages(Set<Image> images) {
        this.images = images;
    }

    /**
     * Gets the category of the clothing item.
     * 
     * @return                      The clothing item's category
     */
    public Category getCategory() {
        return category;
    }

    /**
     * Sets the category of the clothing item.
     * 
     * @param category              The category to associate with the clothing item
     */
    public void setCategory(Category category) {
        this.category = category;
    }
}
