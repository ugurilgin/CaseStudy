package com.testcase.CaseStudy.entities.dto.response;

import java.util.List;

import com.testcase.CaseStudy.core.entites.concretes.Category;
import com.testcase.CaseStudy.core.entites.concretes.Products;

public class ProductResponseDTO {
	
	private Long id;
	private String name;
	private String desc;
	private Integer price;
	private List<Category> category;

	public ProductResponseDTO() {
		
	}

	public ProductResponseDTO(Products products) {
		
		this.id = products.getId();
		this.name = products.getName();
		this.desc = products.getDesc();
		this.price = products.getPrice();
		this.category = products.getCategories().stream().toList();
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
		return desc;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}

	public Integer getPrice() {
		return price;
	}

	public void setPrice(Integer price) {
		this.price = price;
	}

	public List<Category> getCategory() {
		return category;
	}

	public void setCategory(List<Category> category) {
		this.category = category;
	}

	
}
