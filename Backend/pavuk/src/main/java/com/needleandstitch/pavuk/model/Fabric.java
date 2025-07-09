package com.needleandstitch.pavuk.model;

import java.util.Set;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

/**
 * An entity class for fabric.
 * <p>
 * This class serves as a model for the fabric table in the database.
 * </p> 
 * 
 * @author                            Needle &amp; Stitch
 * @version                           1.0.0
 * @since                             15.12.2024
 */
@Entity
@Table(name = "fabrics")
public class Fabric {

    /**
     * The unique identifier for the fabric.
     * <p>
     * This is the primary key of the "fabrics" table, which is auto-generated.
     * </p>
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * The name of the fabric.
     * <p>
     * This is a unique, non-nullable field representing the fabric's name.
     * </p>
     */
    @Column(length = 50, nullable = false, unique = true)
    private String name;

    /**
     * The image associated with the fabric.
     * <p>
     * This is a many-to-one relationship with the Image entity.
     * </p>
     */
    @ManyToOne
    @JoinColumn(name = "image_id", nullable = false)
    private Image image;

    /**
     * The set of colors associated with this fabric.
     * <p>
     * This is a many-to-many relationship with the Color entity.
     * </p>
     */
    @ManyToMany
    @JoinTable(
        name = "fabric_color", 
        joinColumns = @JoinColumn(name = "fabric_id"), 
        inverseJoinColumns = @JoinColumn(name = "color_id")
    )
    private Set<Color> colors;

    /**
     * The set of clothing item parts that use this fabric.
     * <p>
     * This is a one-to-many relationship with the ClothingItemPart entity.
     * </p>
     */
    @OneToMany(mappedBy = "fabric")
    private Set<ClothingItemPart> clothingItemParts;

    /**
     * Default constructor.
     */
    public Fabric() {}

    /**
     * Constructor to create a fabric with a specified name, image, and set of colors.
     * 
     * @param name 						The name of the fabric
     * @param image 					The image associated with the fabric
     * @param colors 					The set of colors associated with the fabric
     */
    public Fabric(String name, Image image, Set<Color> colors) {
        this.name = name;
        this.image = image;
        this.colors = colors;
    }

    /**
     * Gets the unique identifier for the fabric.
     * 
     * @return 							The fabric ID
     */
    public Long getId() {
        return id;
    }

    /**
     * Sets the unique identifier for the fabric.
     * 
     * @param id 						The fabric ID to set
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * Gets the name of the fabric.
     * 
     * @return 							The name of the fabric
     */
    public String getName() {
        return name;
    }

    /**
     * Sets the name of the fabric.
     * 
     * @param name 						The name of the fabric to set
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Gets the image associated with the fabric.
     * 
     * @return 							The image associated with the fabric
     */
    public Image getImage() {
        return image;
    }

    /**
     * Sets the image associated with the fabric.
     * 
     * @param image 					The image to set for the fabric
     */
    public void setImage(Image image) {
        this.image = image;
    }

    /**
     * Gets the set of colors associated with the fabric.
     * 
     * @return 							The set of colors associated with the fabric
     */
    public Set<Color> getColors() {
        return colors;
    }

    /**
     * Sets the set of colors associated with the fabric.
     * 
     * @param colors 					The set of colors to set for the fabric
     */
    public void setColors(Set<Color> colors) {
        this.colors = colors;
    }
}