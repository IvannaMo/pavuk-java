package com.needleandstitch.pavuk.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;


@Entity
@Table(name = "images")
public class Image {
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
	
	@Column(length = 255, nullable = false, unique = true)
    private String name;
	
	@Column(nullable = false, unique = true)
    private String url;
	
	@Column(name = "is_primary", nullable = false)
    private boolean isPrimary;
	
	
	public Image() {}
	
	public Image(String name, String url, boolean isPrimary) {
        this.name = name;
        this.url = url;
        this.isPrimary = isPrimary;
    }
	
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}
	
	public boolean getIsPrimary() {
		return isPrimary;
	}

	public void setIsPrimary(boolean isPrimary) {
		this.isPrimary = isPrimary;
	}
}
