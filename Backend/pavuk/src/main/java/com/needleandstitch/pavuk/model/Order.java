package com.needleandstitch.pavuk.model;

import java.time.LocalDateTime;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

/**
 * An entity class for order.
 * <p>
 * This class serves as a model for the order table in the database.
 * </p> 
 * 
 * @author                            Needle &amp; Stitch
 * @version                           1.0.0
 * @since                             15.12.2024
 */
@Entity
@Table(name = "orders")
public class Order {
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
     * The customer who placed the order.
     * <p>
     * This is a many-to-one relationship with the Customer entity.
     * </p>
     */
    @ManyToOne
    @JoinColumn(name = "customer_id", nullable = false)
    private Customer customer;

    /**
     * The clothing item that was ordered.
     * <p>
     * This is a many-to-one relationship with the ClothingItem entity.
     * </p>
     */
    @ManyToOne
    @JoinColumn(name = "clothing_item_id", nullable = false)
    private ClothingItem clothingItem;

    /**
     * The shipping information for the order.
     * <p>
     * This is a many-to-one relationship with the ShippingInfo entity.
     * </p>
     */
    @ManyToOne
    @JoinColumn(name = "shipping_info_id", nullable = false)
    private ShippingInfo shippingInfo;

    /**
     * The date when the order was placed.
     * <p>
     * This is automatically set to the current timestamp when the order is created.
     * </p>
     */
    @Column(name = "order_date", columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP", nullable = false)
    private LocalDateTime orderDate = LocalDateTime.now();

    /**
     * The status of the order.
     * <p>
     * This is an enumerated field with possible values:  PENDING, ROCESSING, SHIPPED, DELIVERED, and CANCELLED. It is defaulted to PENDING.
     * </p>
     */
    @Enumerated(EnumType.STRING)
    @Column(length = 20, columnDefinition = "VARCHAR(20) DEFAULT 'PENDING'", nullable = false)
    private Status status;

    /**
     * Enum representing the possible statuses of an order.
     */
    public enum Status {
        /**
         * The order is pending and has not been processed yet.
         */
        PENDING,
    
        /**
         * The order is currently being processed.
         */
        PROCESSING,
    
        /**
         * The order has been shipped and is on its way.
         */
        SHIPPED,
    
        /**
         * The order has been delivered to the customer.
         */
        DELIVERED,
    
        /**
         * The order has been cancelled.
         */
        CANCELLED,
    }

    /**
     * Default constructor.
     */
    public Order() {}

    /**
     * Constructor to create a new order with the specified customer, clothing item, and shipping information.
     * 
     * @param customer                  The customer placing the order
     * @param clothingItem              The clothing item being ordered
     * @param shippingInfo              The shipping information for the order
     */
    public Order(Customer customer, ClothingItem clothingItem, ShippingInfo shippingInfo) {
        this.customer = customer;
        this.clothingItem = clothingItem;
        this.shippingInfo = shippingInfo;
        this.orderDate = LocalDateTime.now();
        this.status = Status.PENDING;
    }

    /**
     * Gets the unique identifier for the order.
     * 
     * @return                          The order ID
     */
    public Long getId() {
        return id;
    }

    /**
     * Sets the unique identifier for the order.
     * 
     * @param id                        The order ID to set
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * Gets the customer who placed the order.
     * 
     * @return                          The customer who placed the order
     */
    public Customer getCustomer() {
        return customer;
    }

    /**
     * Sets the customer who placed the order.
     * 
     * @param customer                  The customer to set
     */
    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    /**
     * Gets the clothing item that was ordered.
     * 
     * @return                          The clothing item ordered
     */
    public ClothingItem getClothingItem() {
        return clothingItem;
    }

    /**
     * Sets the clothing item that was ordered.
     * 
     * @param clothingItem              The clothing item to set
     */
    public void setClothingItem(ClothingItem clothingItem) {
        this.clothingItem = clothingItem;
    }

    /**
     * Gets the shipping information for the order.
     * 
     * @return                          The shipping information for the order
     */
    public ShippingInfo getShippingInfo() {
        return shippingInfo;
    }

    /**
     * Sets the shipping information for the order.
     * 
     * @param shippingInfo              The shipping information to set
     */
    public void setShippingInfo(ShippingInfo shippingInfo) {
        this.shippingInfo = shippingInfo;
    }

    /**
     * Gets the date when the order was placed.
     * 
     * @return                          The order date
     */
    public LocalDateTime getOrderDate() {
        return orderDate;
    }

    /**
     * Sets the date when the order was placed.
     * 
     * @param orderDate                 The order date to set
     */
    public void setOrderDate(LocalDateTime orderDate) {
        this.orderDate = orderDate;
    }

    /**
     * Gets the status of the order.
     * 
     * @return                          The status of the order
     */
    public Status getStatus() {
        return status;
    }

    /**
     * Sets the status of the order.
     * 
     * @param status                    The status to set for the order
     */
    public void setStatus(Status status) {
        this.status = status;
    }
}
