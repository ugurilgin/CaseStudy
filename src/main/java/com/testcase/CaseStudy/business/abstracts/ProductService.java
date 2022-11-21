package com.testcase.CaseStudy.business.abstracts;

import java.util.List;

import javax.validation.Valid;

import org.springframework.http.ResponseEntity;

import com.testcase.CaseStudy.entities.dto.request.ProductRequestDTO;
import com.testcase.CaseStudy.entities.dto.response.ProductResponseDTO;

public interface ProductService {
	public List<ProductResponseDTO> getAll();
	public ProductResponseDTO getOneById(Long id);
	public ProductResponseDTO create(@Valid ProductRequestDTO newProduct); 
	public ProductResponseDTO update(Long id,@Valid ProductRequestDTO newProduct);
	public ResponseEntity<?> deleteById(Long id);
	public ResponseEntity<?> deleteAll();
}
