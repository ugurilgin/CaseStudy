package com.testcase.CaseStudy.webapi.controllers;

import java.util.List;

import javax.validation.Valid;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.testcase.CaseStudy.business.abstracts.ProductService;
import com.testcase.CaseStudy.business.concretes.ProductManager;
import com.testcase.CaseStudy.entities.dto.request.ProductRequestDTO;
import com.testcase.CaseStudy.entities.dto.response.ProductResponseDTO;


@RestController
@RequestMapping("/api/product")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class ProductController implements ProductService {
private ProductManager productManager;

	public ProductController(ProductManager productManager) {	
		this.productManager = productManager;
		}

	@GetMapping
	public List<ProductResponseDTO> getAll() {
		return productManager.getAll();
	}

	@GetMapping("/{id}")
	public ProductResponseDTO getOneById(Long id) {
		return productManager.getOneById(id);
	}

	@PostMapping
	public ProductResponseDTO create(@Valid ProductRequestDTO newProduct) {
		return productManager.create(newProduct);
	}

	@PutMapping("/{id}")
	public ProductResponseDTO update(Long id, @Valid ProductRequestDTO newProduct) {
		return productManager.update(id, newProduct);
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<?> deleteById(Long id) {
		return productManager.deleteById(id);
	}

	@DeleteMapping("/all")
	public ResponseEntity<?> deleteAll() {
		return productManager.deleteAll();
	}

}
