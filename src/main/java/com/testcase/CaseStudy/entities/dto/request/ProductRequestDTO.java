package com.testcase.CaseStudy.entities.dto.request;


import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;


public class ProductRequestDTO {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Size(min=1, max=75 ,message = "Category Name Size must be between 1 and 75")
	@NotNull(message = "Category Name can not be blank")
	private String name;
	
	@Lob
	private String descryption;
	
	
	@NotNull(message = "Price can not be blank")
	private Integer price;
	

	public ProductRequestDTO() {
		
	}

	public ProductRequestDTO(Long id,
			@Size(min = 1, max = 75, message = "Category Name Size must be between 1 and 75") @NotNull(message = "Category Name can not be blank") String name,
			 String descryption,
			@NotNull(message = "Price can not be blank") Integer price) {
		super();
		this.id = id;
		this.name = name;
		this.descryption = descryption;
		this.price = price;
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

}
