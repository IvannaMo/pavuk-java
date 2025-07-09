package com.needleandstitch.pavuk.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

/**
 * An entity class for image.
 * <p>
 * This class serves as a model for the image table in the database.
 * </p> 
 * 
 * @author                            Needle &amp; Stitch
 * @version                           1.0.0
 * @since                             15.12.2024
 */
@Entity
@Table(name = "images")
public class Image {

    /**
     * The unique identifier for the image.
     * <p>
     * This is the primary key of the "images" table, which is auto-generated.
     * </p>
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * The name of the image.
     * <p>
     * This is a unique, non-nullable field representing the image's name.
     * </p>
     */
    @Column(length = 255, nullable = false, unique = true)
    private String name;

    /**
     * The URL of the image.
     * <p>
     * This is a unique, non-nullable field representing the URL where the image is hosted.
     * </p>
     */
    @Column(nullable = false, unique = true)
    private String url;

    /**
     * Whether the image is marked as the primary image.
     * <p>
     * This field tells you if this specific is primary for its item.
     * </p>
     */
    @Column(name = "is_primary", nullable = false)
    private boolean isPrimary;

    /**
     * Default constructor.
     */
    public Image() {}

    /**
     * Constructor to create an image with a specified name, URL, and primary status.
     * 
     * @param name 					The name of the image
     * @param url 					The URL of the image
     * @param isPrimary 			Whether the image is marked as the primary image
     */
    public Image(String name, String url, boolean isPrimary) {
        this.name = name;
        this.url = url;
        this.isPrimary = isPrimary;
    }

    /**
     * Gets the unique identifier for the image.
     * 
     * @return 						The image ID
     */
    public Long getId() {
        return id;
    }

    /**
     * Sets the unique identifier for the image.
     * 
     * @param id 					The image ID to set
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * Gets the name of the image.
     * 
     * @return 						The name of the image
     */
    public String getName() {
        return name;
    }

    /**
     * Sets the name of the image.
     * 
     * @param name 					The name of the image to set
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Gets the URL of the image.
     * 
     * @return 						The URL of the image
     */
    public String getUrl() {
        return url;
    }

    /**
     * Sets the URL of the image.
     * 
     * @param url 					The URL to set for the image
     */
    public void setUrl(String url) {
        this.url = url;
    }

    /**
     * Gets whether the image is marked as the primary image.
     * 
     * @return 						True\False depending on whether the image is primary.
     */
    public boolean getIsPrimary() {
        return isPrimary;
    }

    /**
     * Sets whether the image is marked as the primary image.
     * 
     * @param isPrimary 			True\False depending on whether you want to set an image as primary one.
     */
    public void setIsPrimary(boolean isPrimary) {
        this.isPrimary = isPrimary;
    }
}
