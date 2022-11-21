package com.testcase.CaseStudy.business.concretes;

import java.util.List;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.testcase.CaseStudy.business.abstracts.ProductService;
import com.testcase.CaseStudy.core.entites.concretes.Products;
import com.testcase.CaseStudy.core.exceptions.EntityNotFoundException;
import com.testcase.CaseStudy.dataAccess.repositories.ProductRepository;
import com.testcase.CaseStudy.entities.dto.request.ProductRequestDTO;
import com.testcase.CaseStudy.entities.dto.response.ProductResponseDTO;

@Service
public class ProductManager implements ProductService{
private ProductRepository productRepository;
public ProductManager(ProductRepository productRepository) {
	

	this.productRepository = productRepository;
}

public List<ProductResponseDTO> getAll() {
	  List<Products> productList= productRepository.findAll();
	 return productList.stream().map(product -> new ProductResponseDTO(product)).collect(Collectors.toList());
}

public ProductResponseDTO getOneById(Long id) {
	if(id<=0) throw new EntityNotFoundException("Id can not be equal or lower than zero : " + id);
	else {
	Products product =  productRepository.findById(id)
            .orElseThrow(() -> new EntityNotFoundException("Product Not Found with id : " + id));
     return new ProductResponseDTO(product);
	}
}

public ProductResponseDTO create (@Valid ProductRequestDTO newProduct) {
	Products products=new Products(newProduct);
	return new ProductResponseDTO(productRepository.save(products));
}

public ProductResponseDTO update(Long id, @Valid ProductRequestDTO newProduct) {
	if(id<=0) throw new EntityNotFoundException("Id can not be equal or lower than zero : " + id);
	else {
		 return productRepository.findById(id).map(product -> {
			 product.setName(newProduct.getName());
			 product.setDesc(newProduct.getDesc());
			 product.setPrice(newProduct.getPrice());
	         return new ProductResponseDTO( productRepository.save(product));
	     }).orElseThrow(() -> new EntityNotFoundException("Id " + id + " not found"));	
	}

}

public ResponseEntity<?> deleteById(Long id) {
	if(id<=0) throw new EntityNotFoundException("Id can not be equal or lower than zero : " + id);
	else {
	return productRepository.findById(id).map(product -> {
		productRepository.delete(product);
        return ResponseEntity.ok().build();
    }).orElseThrow(() -> new EntityNotFoundException("Product not found with id: " + id ));
	}
}

public ResponseEntity<?> deleteAll() {
	productRepository.deleteAll();
	return ResponseEntity.ok().build();
}


}