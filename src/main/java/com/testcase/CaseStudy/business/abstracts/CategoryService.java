package com.testcase.CaseStudy.business.abstracts;

import java.util.List;

import javax.validation.Valid;

import org.springframework.http.ResponseEntity;

import com.testcase.CaseStudy.entities.concretes.Products;
import com.testcase.CaseStudy.entities.dto.request.CategoryRequestDTO;
import com.testcase.CaseStudy.entities.dto.response.CategoryResponseDTO;



public interface CategoryService {
	public List<CategoryResponseDTO> getAll();
	public ResponseEntity<?> getAllProductsByCategoryId(Long id);
	public CategoryResponseDTO getOneById(Long id);
	public CategoryResponseDTO create(@Valid CategoryRequestDTO newCategory); 
	public ResponseEntity<?> addProduct(Long id, @Valid Products newProduct);
	public CategoryResponseDTO update(Long id,@Valid CategoryRequestDTO newCategory);
	public ResponseEntity<?> deleteById(Long id);
	public ResponseEntity<?> deleteProductFromCategory(Long categoryId, Long productId);
	public ResponseEntity<?> deleteAll();
}
