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
 * An entity class for role.
 * <p>
 * This class serves as a model for the role table in the database.
 * </p> 
 * 
 * @author                            Needle &amp; Stitch
 * @version                           1.0.0
 * @since                             15.12.2024
 */
@Entity
@Table(name = "roles")
public class Role {

    /**
     * The unique identifier for the role.
     * <p>
     * This is the primary key of the "roles" table, which is auto-generated.
     * </p>
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * The name of the role.
     * <p>
     * This is a required field, must be unique, and has a maximum length of 50 characters.
     * </p>
     */
    @Column(length = 50, nullable = false, unique = true)
    private String name;

    /**
     * The set of users associated with this role.
     * <p>
     * This is a one-to-many relationship with the User entity.
     * </p>
     */
    @OneToMany(mappedBy = "role")
    private Set<User> users;

    /**
     * Default constructor.
     */
    public Role() {}

    /**
     * Constructor to create a new role with the specified name.
     * 
     * @param name 					The name of the role
     */
    public Role(String name) {
        this.name = name;
    }

    /**
     * Gets the unique identifier for the role.
     * 
     * @return 						The role ID
     */
    public Long getId() {
        return id;
    }

    /**
     * Sets the unique identifier for the role.
     * 
     * @param id 					The role ID to set
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * Gets the name of the role.
     * 
     * @return 						The role name
     */
    public String getName() {
        return name;
    }

    /**
     * Sets the name of the role.
     * 
     * @param name 					The role name to set
     */
    public void setName(String name) {
        this.name = name;
    }
}