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


@Entity
@Table(name = "fabrics")
public class Fabric {
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
	
	@Column(length = 50, nullable = false, unique = true)
	private String name;
	
	@ManyToOne
    @JoinColumn(name = "image_id", nullable = false)
    private Image image;
	
	@ManyToMany
    @JoinTable(
        name = "fabric_color", 
        joinColumns = @JoinColumn(name = "fabric_id"), 
        inverseJoinColumns = @JoinColumn(name = "color_id")
    )
    private Set<Color> colors;
	
	@OneToMany(mappedBy = "fabric")
	private Set<ClothingItemPart> clothingItemParts;

	
	public Fabric() {}
	
	public Fabric(String name, Image image, Set<Color> colors) {
		this.name = name;
		this.image = image;
		this.colors = colors;
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
	
	public Image getImage() {
        return image;
    }

    public void setImage(Image image) {
        this.image = image;
    }
	
	public Set<Color> getColors() {
        return colors;
    }

    public void setColors(Set<Color> colors) {
        this.colors = colors;
    }
}
