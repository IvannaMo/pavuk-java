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


@Entity
@Table(name = "users")
public class User {
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
	
	@Column(name = "first_name", length = 50, nullable = false)
	private String firstName;
	
	@Column(name = "last_name", length = 50, nullable = false)
	private String lastName;
	
	@Column(name = "date_of_birth", nullable = false)
	private LocalDate dateOfBirth;
	
	@Column(length = 15, nullable = false)
	private String phone;
	
	@Column(length = 255, nullable = false, unique = true)
	private String email;
	
	@Column(name = "newsletter_subscription", nullable = false)
	@Convert(converter = TrueFalseConverter.class)
	private Boolean newsletterSubscription;
	
	@Column(length = 255, nullable = false)
	private String password;
	
	@ManyToOne
    @JoinColumn(name = "role_id", referencedColumnName = "id", nullable = false)
	private Role role;
	
	@OneToOne(mappedBy = "user")
    private Tailor tailor;
	
	@Enumerated(EnumType.STRING)
	@Column(length = 20, columnDefinition = "VARCHAR(20) DEFAULT 'OFFLINE'", nullable = false)
	private Status status;
	
	@Column(name = "registration_date", columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP", nullable = false)
	private LocalDateTime registrationDate;
	
	@Column(name = "online_date")
	private LocalDateTime onlineDate;
	
	
	public enum Status {
	    ONLINE, IDLE, OFFLINE, BANNED
	}
	
	
	public User() {}
	
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
	
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
	
	public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public LocalDate getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(LocalDate dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
    
    public Boolean getNewsletterSubscription() {
        return newsletterSubscription;
    }

    public void setNewsletterSubscription(Boolean newsletterSubscription) {
        this.newsletterSubscription = newsletterSubscription;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public Tailor getTailor() {
        return tailor;
    }

    public void setTailor(Tailor tailor) {
        this.tailor = tailor;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public LocalDateTime getRegistrationDate() {
        return registrationDate;
    }

    public void setRegistrationDate(LocalDateTime registrationDate) {
        this.registrationDate = registrationDate;
    }

    public LocalDateTime getOnlineDate() {
        return onlineDate;
    }

    public void setOnlineDate(LocalDateTime onlineDate) {
        this.onlineDate = onlineDate;
    }
}
