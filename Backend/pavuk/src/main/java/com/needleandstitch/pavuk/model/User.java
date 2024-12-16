package com.needleandstitch.pavuk.model;

import java.time.LocalDate;
import java.time.LocalDateTime;
import org.hibernate.type.TrueFalseConverter;
import jakarta.persistence.Column;
import jakarta.persistence.Convert;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

/**
 * An entity class for user.
 * <p>
 * This class serves as a model for the user table in the database.
 * </p> 
 * 
 * @author                            Needle &amp; Stitch
 * @version                           1.0.0
 * @since                             15.12.2024
 */
@Entity
@Table(name = "users")
public class User {
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
     * The user's first name.
     * <p>
     * This is a required field with a maximum length of 50 characters.
     * </p>
     */
	@Column(name = "first_name", length = 50, nullable = false)
	private String firstName;
	
     /**
     * The user's last name.
     * <p>
     * This is a required field with a maximum length of 50 characters.
     * </p>
     */
	@Column(name = "last_name", length = 50, nullable = false)
	private String lastName;
	
     /**
     * The user's date of birth.
     * <p>
     * This is a required field.
     * </p>
     */
	@Column(name = "date_of_birth", nullable = false)
	private LocalDate dateOfBirth;
	
     /**
     * The user's phone.
     * <p>
     * This is a required field with a maximum length of 15 characters.
     * </p>
     */
	@Column(length = 15, nullable = false)
	private String phone;
	
     /**
     * The user's email.
     * <p>
     * This is a required field.
     * </p>
     */
	@Column(length = 255, nullable = false, unique = true)
	private String email;
	
    /**
     * Whether the user is subscribed to the newsletter.
     * <p>
     * This is a required field.
     * </p>
     */
	@Column(name = "newsletter_subscription", nullable = false)
	@Convert(converter = TrueFalseConverter.class)
	private Boolean newsletterSubscription;
	
    /**
     * The user's password.
     * <p>
     * This is a required field.
     * </p>
     */
	@Column(length = 255, nullable = false)
	private String password;

	 /**
     * The role assigned to the user.
     * <p>
     * This is a many-to-one relationship with the Role entity. It is a required field.
     * </p>
     */
	@ManyToOne
    @JoinColumn(name = "role_id", referencedColumnName = "id", nullable = false)
	private Role role;
	
     /**
     * The tailor chosen by the user.
     * <p>
     * This is a one-to-one relationship with the Tailor entity.
     * </p>
     */
	@OneToOne(mappedBy = "user")
    private Tailor tailor;
	
     /**
     * The status of the user.
     * <p>
     * This is an enumerated field with possible values: ONLINE, IDLE, OFFLINE, and BANNED. It is a required field.
     * </p>
     */
	@Enumerated(EnumType.STRING)
	@Column(length = 20, columnDefinition = "VARCHAR(20) DEFAULT 'OFFLINE'", nullable = false)
	private Status status;
	
    /**
     * User's registation date.
     * <p>
     * It is a required field.
     * </p>
     */
	@Column(name = "registration_date", columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP", nullable = false)
	private LocalDateTime registrationDate;
	
    /**
     * The last time user was online.
     */
	@Column(name = "online_date")
	private LocalDateTime onlineDate;
	
	/**
    *Enum representing the status of a user.
    */
    public enum Status {
        /**
         * The user is currently online and active.
         */
        ONLINE,

        /**
         * The user is idle and not actively interacting, but still logged in.
         */
        IDLE,

        /**
         * The user is not online or logged in.
         */
        OFFLINE,

        /**
         * The user has been banned and cannot access the platform.
         */
        BANNED
    }
	
	  /**
     * Default constructor.
     */
	public User() {}
	
     /**
     * Constructor to create a new user with the specified information.
     * 
     * @param firstName              The user's first name
     * @param lastName               The user's last name
     * @param dateOfBirth            The user's date of birth
     * @param phone                  The user's phone number
     * @param email                  The user's email address
     * @param newsletterSubscription Whether the user is subscribed to the newsletter
     * @param password               The user's password
     */
	public User(String firstName, String lastName, LocalDate dateOfBirth, String phone, String email, Boolean newsletterSubscription, String password) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.dateOfBirth = dateOfBirth;
        this.phone = phone;
        this.email = email;
        this.newsletterSubscription = newsletterSubscription;
        this.password = password;
        this.role = new Role();
        this.tailor = null;
        this.status = Status.OFFLINE;
        this.registrationDate = LocalDateTime.now();
        this.onlineDate = null;
    }
	
     /**
     * Constructor to create a new user with the specified information.
     * 
     * @param firstName              The user's first name
     * @param lastName               The user's last name
     * @param dateOfBirth            The user's date of birth
     * @param phone                  The user's phone number
     * @param email                  The user's email address
     * @param newsletterSubscription Whether the user is subscribed to the newsletter
     * @param password               The user's password
     * @param role                   The role assigned to the user
     */
	public User(String firstName, String lastName, LocalDate dateOfBirth, String phone, String email, Boolean newsletterSubscription, String password, Role role) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.dateOfBirth = dateOfBirth;
        this.phone = phone;
        this.email = email;
        this.newsletterSubscription = newsletterSubscription;
        this.password = password;
        this.role = role;
        this.tailor = null;
        this.status = Status.OFFLINE;
        this.registrationDate = LocalDateTime.now();
        this.onlineDate = null;
    }
	
	
    /**
     * Gets the unique identifier of the user.
     * 
     * @return 						The user ID
     */
	public Long getId() {
		return id;
	}

    /**
     * Sets the unique identifier of the user.
     * 
     * @param id					The user ID to set
     */
	public void setId(Long id) {
		this.id = id;
	}
	
    /**
     * Gets the first name of the user.
     * 
     * @return						The first name of the user
     */
	public String getFirstName() {
        return firstName;
    }

    /**
     * Sets the first name of the user.
     * 
     * @param firstName				The first name of the user to set
     */
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    /**
     * Gets the last name of the user.
     * 
     * @return 						The last name of the user
     */
    public String getLastName() {
        return lastName;
    }

    /**
     * Sets the last name of the user.
     * 
     * @param lastName 				The last name of the user to set
     */
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    /**
     * Gets the date of birth of the user.
     * 
     * @return 						User's date of birth
     */
    public LocalDate getDateOfBirth() {
        return dateOfBirth;
    }

    /**
     * Sets the date of birth of the user.
     * 
     * @param dateOfBirth			User's date of birth to set
     */
    public void setDateOfBirth(LocalDate dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    /**
     * Gets the phone number of the user.
     * 
     * @return						User's phone number
     */
    public String getPhone() {
        return phone;
    }

    /**
     * Sets the phone number of the user.
     * 
     * @param phone					User's phone number to set
     */
    public void setPhone(String phone) {
        this.phone = phone;
    }

    /**
     * Gets the email of the user.
     * 
     * @return 						User's email
     */
    public String getEmail() {
        return email;
    }

    /**
     * Sets the email of the user.
     * 
     * @param email 				User's email to set
     */
    public void setEmail(String email) {
        this.email = email;
    }
    
    /**
     * Gets the whether the user is subscribed to the newsletter.
     * 
     * @return 						True\False depending on subscription status
     */
    public Boolean getNewsletterSubscription() {
        return newsletterSubscription;
    }

    /**
     * Sets the whether the user is subscribed to the newsletter.
     * 
     * @param newsletterSubscription True\False depending on subscription status
     */
    public void setNewsletterSubscription(Boolean newsletterSubscription) {
        this.newsletterSubscription = newsletterSubscription;
    }

    /**
     * Gets the password of the user.
     * 
     * @return 						User's password
     */
    public String getPassword() {
        return password;
    }

    /**
     * Sets the password of the user.
     * 
     * @param password 				User's password to set
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * Gets the role of the user.
     * 
     * @return 						User's role 
     */
    public Role getRole() {
        return role;
    }

    /**
     * Sets the role of the user.
     * 
     * @param role 					User's role to set
     */
    public void setRole(Role role) {
        this.role = role;
    }

    /**
     * Gets the tailor of the user.
     * 
     * @return						User's tailor
     */
    public Tailor getTailor() {
        return tailor;
    }

    /**
     * Sets the status of the user.
     * 
     * @param tailor 				User's tailor to set
     */
    public void setTailor(Tailor tailor) {
        this.tailor = tailor;
    }

    /**
     * Gets the status of the user.
     * 
     * @return						User's status
     */
    public Status getStatus() {
        return status;
    }

    /**
     * Sets the status of the user.
     * 
     * @param status				User's status to set
     */
    public void setStatus(Status status) {
        this.status = status;
    }

    /**
     * Gets the registration date of the user.
     * 
     * @return 						User's registration date
     */
    public LocalDateTime getRegistrationDate() {
        return registrationDate;
    }

    /**
     * Sets the registration date of the user.
     * 
     * @param registrationDate 		User's registration date to set
     */
    public void setRegistrationDate(LocalDateTime registrationDate) {
        this.registrationDate = registrationDate;
    }

    /**
     * Gets the last time user was online.
     * 
     * @return 						The last time user was online
     */
    public LocalDateTime getOnlineDate() {
        return onlineDate;
    }

    /**
     * Sets the last time user was online.
     * 
     * @param onlineDate 			The last time user was online to set
     */
    public void setOnlineDate(LocalDateTime onlineDate) {
        this.onlineDate = onlineDate;
    }
}
