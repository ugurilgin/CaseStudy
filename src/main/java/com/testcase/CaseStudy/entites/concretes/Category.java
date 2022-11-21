package com.testcase.CaseStudy.entites.concretes;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import com.testcase.CaseStudy.entities.dto.request.CategoryRequestDTO;

import lombok.Data;

@Data
@Entity
@Table(name="categories")
public class Category {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Size(min=3, max=25 ,message = "Category Name Size must be between 3 and 25")
	@NotBlank(message = "Category Name can not be empty")
	private String name;

	 @ManyToMany(fetch = FetchType.LAZY,
		      cascade = {
		          CascadeType.PERSIST,
		          CascadeType.MERGE
		      })
		  @JoinTable(name = "category_products",
		        joinColumns = { @JoinColumn(name = "category_id") },
		        inverseJoinColumns = { @JoinColumn(name = "products_id") })
	  private Set<Products> products = new HashSet<>();
	 public Category() {
			
		}
	 
	public Category(Long id,
			@Size(min = 3, max = 25, message = "Category Name Size must be between 3 and 25") @NotBlank(message = "Category Name can not be empty") String name,
			Set<Products> products) {
		
		this.id = id;
		this.name = name;
		this.products = products;
	}

	public Category(@Valid CategoryRequestDTO newCategory) {
		this.name = newCategory.getName();
		
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

	
	public Set<Products> getProducts() {
		return products;
	}

	public void setProducts(Set<Products> products) {
		this.products = products;
	}

	public void addProduct(Products products) {
    this.products.add(products);
    products.getCategories().add(this);
    }
		  
    public void removeProduct(long productId) {
	Products products = this.products.stream().filter(t -> t.getId() == productId).findFirst().orElse(null);
    if (products != null) {
    this.products.remove(products);
    products.getCategories().remove(this);
	  } 
    }

	
	

}
