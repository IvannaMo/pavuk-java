package com.needleandstitch.pavuk.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

/**
 * An entity class for clothing item part.
 * <p>
 * This class serves as a model for the clothing item part table in the database.
 * </p> 
 * 
 * @author                            Needle & Stitch
 * @version                           1.0.0
 * @since                             15.12.2024
 */
@Entity
@Table(name = "clothing_item_parts")
public class ClothingItemPart {

    /**
     * The unique identifier for the clothing item part.
     * <p>
     * Serves as the primary key of the "clothing_item_parts" table, auto-generated.
     * </p>
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * The clothing item that this part belongs to.
     * <p>
     * This is a many-to-one relationship with ClothingItem.
     * </p>
     */
    @ManyToOne
    @JoinColumn(name = "clothing_item_id", nullable = false)
    private ClothingItem clothingItem;

    /**
     * The image associated with this clothing item part.
     * <p>
     * This is a many-to-one relationship with Image.
     * </p>
     */
    @ManyToOne
    @JoinColumn(name = "image_id", nullable = false)
    private Image image;

    /**
     * The fabric type used for this part of the clothing item.
     * <p>
     * This is a many-to-one relationship with Fabric.
     * </p>
     */
    @ManyToOne
    @JoinColumn(name = "fabric_id", nullable = false)
    private Fabric fabric;

    /**
     * Default constructor.
     */
    public ClothingItemPart() {}

    /**
     * Constructor for creating a clothing item part with specific clothing item, image, and fabric.
     * 
     * @param clothingItem            The clothing item to associate with this part
     * @param image                   The image to associate with this part
     * @param fabric                  The fabric to associate with this part
     */
    public ClothingItemPart(ClothingItem clothingItem, Image image, Fabric fabric) {
        this.clothingItem = clothingItem;
        this.image = image;
        this.fabric = fabric;
    }

    /**
     * Gets the unique identifier of the clothing item part.
     * 
     * @return                        The clothing item part ID
     */
    public Long getId() {
        return id;
    }

    /**
     * Sets the unique identifier for the clothing item part.
     * 
     * @param id                      The clothing item part ID to set
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * Gets the clothing item associated with this part.
     * 
     * @return                        The clothing item
     */
    public ClothingItem getClothingItem() {
        return clothingItem;
    }

    /**
     * Sets the clothing item associated with this part.
     * 
     * @param clothingItem            The clothing item to set
     */
    public void setClothingItem(ClothingItem clothingItem) {
        this.clothingItem = clothingItem;
    }

    /**
     * Gets the image associated with this clothing item part.
     * 
     * @return                        The image
     */
    public Image getImage() {
        return image;
    }

    /**
     * Sets the image associated with this clothing item part.
     * 
     * @param image                   The image to set
     */
    public void setImage(Image image) {
        this.image = image;
    }

    /**
     * Gets the fabric used for this clothing item part.
     * 
     * @return                        The fabric
     */
    public Fabric getFabric() {
        return fabric;
    }

    /**
     * Sets the fabric used for this clothing item part.
     * 
     * @param fabric                  The fabric to set
     */
    public void setFabric(Fabric fabric) {
        this.fabric = fabric;
    }
}
