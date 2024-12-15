package com.needleandstitch.pavuk.model;

import java.util.Set;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;

/**
 * An entity class for color.
 * <p>
 * This class serves as a model for the color table in the database.
 * </p> 
 * 
 * @author                            Needle & Stitch
 * @version                           1.0.0
 * @since                             15.12.2024
 */
@Entity
@Table(name = "colors")
public class Color {

    /**
     * The unique identifier for the color.
     * <p>
     * This is the primary key of the "colors" table, auto-generated.
     * </p>
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * The hex code representing the color.
     * <p>
     * The field is constrained to 50 characters and cannot be null.
     * </p>
     */
    @Column(length = 50, nullable = false, unique = true)
    private String hex;

    /**
     * The fabrics associated with this color.
     * <p>
     * This is a many-to-many relationship with Fabric.
     * </p>
     */
    @ManyToMany(mappedBy = "colors")
    private Set<Fabric> fabrics;

    /**
     * Default constructor.
     */
    public Color() {}

    /**
     * Constructor to create a color with a specified hex code.
     * 
     * @param hex 					The hex code representing the color
     */
    public Color(String hex) {
        this.hex = hex;
    }

    /**
     * Gets the unique identifier for the color.
     * 
     * @return 						The color ID
     */
    public Long getId() {
        return id;
    }

    /**
     * Sets the unique identifier for the color.
     * 
     * @param id 					The color ID to set
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * Gets the hex code representing the color.
     * 
     * @return 						The hex code of the color
     */
    public String getHex() {
        return hex;
    }

    /**
     * Sets the hex code representing the color.
     * 
     * @param hex 					The hex code to set for the color
     */
    public void setHex(String hex) {
        this.hex = hex;
    }
}
