package com.testcase.CaseStudy.entities.dto.request;

import java.util.Set;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import com.testcase.CaseStudy.entites.concretes.Products;

public class CategoryRequestDTO {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Size(min=3, max=25 ,message = "Category Name Size must be between 3 and 25")
	@NotBlank(message = "Category Name can not be empty")
	private String name;


	 public CategoryRequestDTO() {
			
		}
	 
	public CategoryRequestDTO(Long id,
			@Size(min = 3, max = 25, message = "Category Name Size must be between 3 and 25") @NotBlank(message = "Category Name can not be empty") String name,
			Set<Products> products) {
		
		this.id = id;
		this.name = name;
		
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

	

	
}
