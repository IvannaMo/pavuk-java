package com.needleandstitch.pavuk.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

/**
 * An entity class for tailor.
 * <p>
 * This class serves as a model for the tailor table in the database.
 * </p> 
 * 
 * @author                            Needle &amp; Stitch
 * @version                           1.0.0
 * @since                             15.12.2024
 */
@Entity
@Table(name = "tailors")
public class Tailor {

    /**
     * The unique identifier for the tailor.
     * <p>
     * This is the primary key of the "tailor" table, which is auto-generated.
     * </p>
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * The associated user for the tailor.
     * <p>
     * This is a one-to-one relationship with the User entity.
     * </p>
     */
    @OneToOne
    @JoinColumn(name = "user_id", referencedColumnName = "id", nullable = false)
    private User user;

    /**
     * The number of years the tailor has been working.
     * <p>
     * The fiels is required.
     * </p>
     */
    @Column(name = "years_of_experience", nullable = false)
    private int yearsOfExperience;

    /**
     * Default constructor.
     */
    public Tailor() {}

    /**
     * Constructor to create a new tailor.
     * 
     * @param user                  The associated user for the tailor
     * @param yearsOfExperience     The number of years of experience the tailor has
     */
    public Tailor(User user, int yearsOfExperience) {
        this.user = user;
        this.yearsOfExperience = yearsOfExperience;
    }

    /**
     * Gets the unique identifier for the tailor.
     * 
     * @return                      The tailor ID
     */
    public Long getId() {
        return id;
    }

    /**
     * Sets the unique identifier for the tailor.
     * 
     * @param id                    The tailor ID to set
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * Gets the associated user for the tailor.
     * 
     * @return                      The user associated with the tailor
     */
    public User getUser() {
        return user;
    }

    /**
     * Sets the associated user for the tailor.
     * 
     * @param user                  The user to set
     */
    public void setUser(User user) {
        this.user = user;
    }

    /**
     * Gets the number of years of experience the tailor has.
     * 
     * @return                      The years of experience
     */
    public int getYearsOfExperience() {
        return yearsOfExperience;
    }

    /**
     * Sets the number of years of experience the tailor has.
     * 
     * @param yearsOfExperience     The years of experience to set
     */
    public void setYearsOfExperience(int yearsOfExperience) {
        this.yearsOfExperience = yearsOfExperience;
    }
}
