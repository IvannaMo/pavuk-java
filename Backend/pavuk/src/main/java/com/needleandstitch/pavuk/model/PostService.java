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
 * An entity class for post service.
 * <p>
 * This class serves as a model for the post service table in the database.
 * </p> 
 * 
 * @author                            Needle &amp; Stitch
 * @version                           1.0.0
 * @since                             15.12.2024
 */
@Entity
@Table(name = "post_services")
public class PostService {
	 /**
     * The unique identifier for the order.
     * <p>
     * This is the primary key of the "orders" table, which is auto-generated.
     * </p>
     */
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
	
	/**
     * The name of the post service.
     * <p>
     * This is a unique, non-nullable field representing the post service's name.
     * </p>
     */
	@Column(length = 50, nullable = false, unique = true)
	private String name;

	@OneToMany(mappedBy = "postService")
    private Set<ShippingInfo> shippingInfo;
	
	/**
     * Default constructor.
     */
	public PostService() {}
	
	public PostService(String name) {
		this.name = name;
	}
	
	/**
     * Gets the unique identifier for the post service.
     * 
     * @return                          The post service ID
     */
	public Long getId() {
		return id;
	}

	/**
     * Sets the unique identifier for the post service.
     * 
     * @param id                        The post service ID
     */
	public void setId(Long id) {
		this.id = id;
	}
	
	/**
     * Gets the name of the post service.
     * 
     * @return 					The name of the post service
     */
	public String getName() {
		return name;
	}

	/**
     * Sets the name of the post service.
     * 
     * @param name 					The name of the post service
     */
	public void setName(String name) {
		this.name = name;
	}
}
