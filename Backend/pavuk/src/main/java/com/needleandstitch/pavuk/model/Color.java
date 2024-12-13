package com.needleandstitch.pavuk.model;

import java.util.Set;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;


@Entity
@Table(name = "colors")
public class Color {
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
	
	@Column(length = 50, nullable = false, unique = true)
	private String hex;
	
	@ManyToMany(mappedBy = "colors")
	private Set<Fabric> fabrics;
	
	
	public Color() {}
	
	public Color(String hex) {
		this.hex = hex;
	}
	
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
	
	public String getHex() {
		return hex;
	}

	public void setHex(String hex) {
		this.hex = hex;
	}
}
