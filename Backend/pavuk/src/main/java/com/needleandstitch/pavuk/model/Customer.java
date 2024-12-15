package com.needleandstitch.pavuk.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

/**
 * An entity class for customer.
 * <p>
 * This class serves as a model for the customer table in the database.
 * </p> 
 * 
 * @author                            Needle & Stitch
 * @version                           1.0.0
 * @since                             15.12.2024
 */
@Entity
@Table(name = "customers")
public class Customer {

    /**
     * The unique identifier for the customer.
     * <p>
     * This is the primary key of the "customers" table, auto-generated.
     * </p>
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * The user associated with this customer.
     * <p>
     * This is a one-to-one relationship with the User entity.
     * </p>
     */
    @OneToOne
    @JoinColumn(name = "user_id", referencedColumnName = "id", nullable = false)
    private User user;

    /**
     * Default constructor.
     */
    public Customer() {}

    /**
     * Constructor to create a customer with a specified user.
     * 
     * @param user 					The user associated with this customer
     */
    public Customer(User user) {
        this.user = user;
    }

    /**
     * Gets the unique identifier for the customer.
     * 
     * @return 						The customer ID
     */
    public Long getId() {
        return id;
    }

    /**
     * Sets the unique identifier for the customer.
     * 
     * @param id 					The customer ID to set
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * Gets the user associated with this customer.
     * 
     * @return 						The user associated with this customer
     */
    public User getUser() {
        return user;
    }

    /**
     * Sets the user associated with this customer.
     * 
     * @param user 					The user to set for this customer
     */
    public void setUser(User user) {
        this.user = user;
    }
}
