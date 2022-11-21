package com.testcase.CaseStudy.entities.concretes;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.testcase.CaseStudy.entities.dto.request.ProductRequestDTO;

import lombok.Data;

@Data
@Entity
@Table(name="products")
public class Products {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Size(min=1, max=75 ,message = "Product Name Size must be between 1 and 75")
	@NotBlank(message = "Product Name can not be blank")
	private String name;
	
	@Lob
	private String descryption;
	
	
	@NotNull(message = "Price can not be blank")
	private Integer price;
	
	@ManyToMany(fetch = FetchType.LAZY,
		      cascade = {
		          CascadeType.PERSIST,
		          CascadeType.MERGE,
		          CascadeType.REFRESH
		      },
		      mappedBy = "products")
		  @JsonIgnore
		  private Set<Category> categories = new HashSet<>();

	public Products() {
		
	}

	public Products(Long id,
			@Size(min = 1, max = 75, message = "Category Name Size must be between 1 and 75") @NotNull(message = "Category Name can not be blank") String name,
			 String descryption,
			@NotNull(message = "Price can not be blank") Integer price, Set<Category> categories) {
		
		this.id = id;
		this.name = name;
		this.descryption = descryption;
		this.price = price;
		this.categories = categories;
	}

	public Products(@Valid ProductRequestDTO newProduct) {
		this.name = newProduct.getName();
		this.descryption = newProduct.getDesc();
		this.price = newProduct.getPrice();
	
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

	public String getDesc() {
		return descryption;
	}

	public void setDesc(String descryption) {
		this.descryption = descryption;
	}

	public Integer getPrice() {
		return price;
	}

	public void setPrice(Integer price) {
		this.price = price;
	}

	public Set<Category> getCategories() {
		return categories;
	}

	public void setCategories(Set<Category> categories) {
		this.categories = categories;
	}
	
	
}
