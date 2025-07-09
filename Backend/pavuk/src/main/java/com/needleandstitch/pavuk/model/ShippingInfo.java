package com.needleandstitch.pavuk.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

/**
 * An entity class for shipping info.
 * <p>
 * This class serves as a model for the shipping info table in the database.
 * </p> 
 * 
 * @author                            Needle &amp; Stitch
 * @version                           1.0.0
 * @since                             15.12.2024
 */
@Entity
@Table(name = "shipping_info")
public class ShippingInfo {
    /**
     * The unique identifier for the shipping information.
     * <p>
     * This is the primary key of the "shipping_info" table, which is auto-generated.
     * </p>
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * The country of the shipping address.
     * <p>
     * This field is required and has a maximum length of 50 characters.
     * </p>
     */
    @Column(length = 50, nullable = false)
    private String country;

    /**
     * The region (or state) of the shipping address.
     * <p>
     * This field is nullable and has a maximum length of 50 characters.
     * </p>
     */
    @Column(length = 50)
    private String region;

    /**
     * The city of the shipping address.
     * <p>
     * This field is required and has a maximum length of 50 characters.
     * </p>
     */
    @Column(length = 50, nullable = false)
    private String city;

    /**
     * The postal code of the shipping address.
     * <p>
     * This field is required and has a maximum length of 20 characters.
     * </p>
     */
    @Column(name = "postal_code", length = 20, nullable = false)
    private String postalCode;

    /**
     * The post service used for shipping.
     * <p>
     * This is a many-to-one relationship with the PostService entity, where each shipping info has one post service.
     * </p>
     */
    @ManyToOne
    @JoinColumn(name = "post_service_id", nullable = false)
    private PostService postService;

    /**
     * Default constructor.
     */
    public ShippingInfo() {}

    /**
     * Constructor to create a new shipping information record.
     * 
     * @param country               The country of the shipping address
     * @param region                The region (state) of the shipping address
     * @param city                  The city of the shipping address
     * @param postalCode            The postal code of the shipping address
     * @param postService           The post service used for shipping
     */
    public ShippingInfo(String country, String region, String city, String postalCode, PostService postService) {
        this.country = country;
        this.region = region;
        this.city = city;
        this.postalCode = postalCode;
        this.postService = postService;
    }

    /**
     * Gets the unique identifier for the shipping information.
     * 
     * @return                      The shipping information ID
     */
    public Long getId() {
        return id;
    }

    /**
     * Sets the unique identifier for the shipping information.
     * 
     * @param id                    The shipping information ID to set
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * Gets the country of the shipping address.
     * 
     * @return                      The country
     */
    public String getCountry() {
        return country;
    }

    /**
     * Sets the country of the shipping address.
     * 
     * @param country               The country to set
     */
    public void setCountry(String country) {
        this.country = country;
    }

    /**
     * Gets the region (state) of the shipping address.
     * 
     * @return                      The region
     */
    public String getRegion() {
        return region;
    }

    /**
     * Sets the region (state) of the shipping address.
     * 
     * @param region                The region to set
     */
    public void setRegion(String region) {
        this.region = region;
    }

    /**
     * Gets the city of the shipping address.
     * 
     * @return                      The city
     */
    public String getCity() {
        return city;
    }

    /**
     * Sets the city of the shipping address.
     * 
     * @param city                  The city to set
     */
    public void setCity(String city) {
        this.city = city;
    }

    /**
     * Gets the postal code of the shipping address.
     * 
     * @return                      The postal code
     */
    public String getPostalCode() {
        return postalCode;
    }

    /**
     * Sets the postal code of the shipping address.
     * 
     * @param postalCode            The postal code to set
     */
    public void setPostalCode(String postalCode) {
        this.postalCode = postalCode;
    }

    /**
     * Gets the post service used for shipping.
     * 
     * @return                      The post service
     */
    public PostService getPostService() {
        return postService;
    }

    /**
     * Sets the post service used for shipping.
     * 
     * @param postService           The post service to set
     */
    public void setPostService(PostService postService) {
        this.postService = postService;
    }
}
