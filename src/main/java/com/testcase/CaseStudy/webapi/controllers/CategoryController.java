package com.testcase.CaseStudy.webapi.controllers;

import java.util.List;

import javax.validation.Valid;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.testcase.CaseStudy.business.abstracts.CategoryService;
import com.testcase.CaseStudy.business.concretes.CategoryManager;
import com.testcase.CaseStudy.entities.dto.request.CategoryRequestDTO;
import com.testcase.CaseStudy.entities.dto.request.ProductRequestDTO;
import com.testcase.CaseStudy.entities.dto.response.CategoryResponseDTO;

@RestController
@RequestMapping("/api/category")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class CategoryController implements CategoryService {
	private CategoryManager categoryManager;

	public CategoryController(CategoryManager categoryManager) {	
		this.categoryManager = categoryManager;
	}

	@GetMapping
	public List<CategoryResponseDTO> getAll() {
		
		return categoryManager.getAll();
	}

	@GetMapping("/{id}/products")
	public ResponseEntity<?> getAllProductsByCategoryId(@PathVariable Long id) {
		return categoryManager.getAllProductsByCategoryId(id);
	}

	@GetMapping("/{id}")
	public CategoryResponseDTO getOneById(@PathVariable Long id) {
		return categoryManager.getOneById(id);
	}

	@PostMapping
	public CategoryResponseDTO create(@Valid @RequestBody CategoryRequestDTO newCategory) {
		return categoryManager.create(newCategory);
	}

	@PostMapping("/{id}/products")
	public ResponseEntity<?> addProduct(@PathVariable Long id, @Valid @RequestBody ProductRequestDTO newProduct) {
		return categoryManager.addProduct(id,newProduct);
	}

	@PutMapping("/{id}")
	public CategoryResponseDTO update(@PathVariable Long id, @Valid @RequestBody CategoryRequestDTO newCategory) {
		return categoryManager.update(id,newCategory);
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<?> deleteById(@PathVariable Long id) {
		return categoryManager.deleteById(id);
	}

	@DeleteMapping("/{categoryId}/products/{productId}")
	public ResponseEntity<?> deleteProductFromCategory(@PathVariable Long categoryId,@PathVariable Long productId) {
		return categoryManager.deleteProductFromCategory(categoryId,productId);
	}

	@DeleteMapping("/all")
	public ResponseEntity<?> deleteAll() {
		return categoryManager.deleteAll();
	}


}
