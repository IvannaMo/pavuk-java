package com.needleandstitch.pavuk.model;

import java.math.BigDecimal;
import java.util.Set;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;


@Entity
@Table(name = "clothing_items")
public class ClothingItem {
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
	
	@Column(length = 100, nullable = false, unique = true)
    private String name;
	
	@Column(length = 500)
	private String description;

	@Column(nullable = false)
	private BigDecimal price;

	@OneToMany
    @JoinTable(
        name = "clothing_item_images",
        joinColumns = @JoinColumn(name = "clothing_item_id"),
        inverseJoinColumns = @JoinColumn(name = "image_id")
    )
    private Set<Image> images;
	
	@OneToMany(mappedBy = "clothingItem")
    private Set<ClothingItemPart> parts;
	
	@ManyToOne
	@JoinColumn(name = "category_id", referencedColumnName = "id", nullable = false)
	private Category category;
	 
	 
	public ClothingItem() {}
	 
	public ClothingItem(String name, String description, BigDecimal price, Set<Image> images, Category category) {
		this.name = name;
		this.description = description;
		this.price = price;
		this.images = images;
		this.category = category;
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }
    
    public Set<Image> getImages() {
        return images;
    }

    public void setImages(Set<Image> images) {
        this.images = images;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }
}
