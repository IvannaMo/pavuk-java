package com.needleandstitch.pavuk.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;


@Entity
@Table(name = "shipping_info")
public class ShippingInfo {
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 50, nullable = false)
    private String country;

    @Column(length = 50)
    private String region;
    
    @Column(length = 50, nullable = false)
    private String city;

    @Column(name = "postal_code", length = 20, nullable = false)
    private String postalCode;
    
    @ManyToOne
    @JoinColumn(name = "post_service_id", nullable = false)
    private PostService postService;
    

    public ShippingInfo() {}
    
    public ShippingInfo(String country, String region, String city, String postalCode, PostService postService) {
        this.country = country;
        this.region = region;
        this.city = city;
        this.postalCode = postalCode;
        this.postService = postService;
    }
    
    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }
    
    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    } 
    
    public String getPostalCode() {
        return postalCode;
    }

    public void setPostalCode(String postalCode) {
        this.postalCode = postalCode;
    }

    public PostService getPostService() {
        return postService;
    }

    public void setPostService(PostService postService) {
        this.postService = postService;
    }
}
