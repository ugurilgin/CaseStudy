package com.testcase.CaseStudy.entities.dto.response;


import com.testcase.CaseStudy.entities.concretes.Category;

public class CategoryResponseDTO {
	
	private Long id;
	private String name;

	 public CategoryResponseDTO() {
			
		}
	 
	public CategoryResponseDTO(Category category) {
		
		this.id = category.getId();
		this.name = category.getName();
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
