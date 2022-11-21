package com.testcase.CaseStudy.business.concretes;

import java.util.List;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.testcase.CaseStudy.business.abstracts.CategoryService;
import com.testcase.CaseStudy.core.exceptions.EntityNotFoundException;
import com.testcase.CaseStudy.dataAccess.repositories.CategoryRepository;
import com.testcase.CaseStudy.dataAccess.repositories.ProductRepository;
import com.testcase.CaseStudy.entities.concretes.Category;
import com.testcase.CaseStudy.entities.concretes.Products;
import com.testcase.CaseStudy.entities.dto.request.CategoryRequestDTO;
import com.testcase.CaseStudy.entities.dto.request.ProductRequestDTO;
import com.testcase.CaseStudy.entities.dto.response.CategoryResponseDTO;


@Service
public class CategoryManager implements CategoryService{
private CategoryRepository categoryRepository;
private ProductRepository productRepository;
public CategoryManager(CategoryRepository categoryRepository,ProductRepository productRepository) {
	
	this.categoryRepository = categoryRepository;
	this.productRepository = productRepository;
}


public List<CategoryResponseDTO> getAll() {
	  List<Category> categoryList= categoryRepository.findAll();
	 return categoryList.stream().map(category -> new CategoryResponseDTO(category)).collect(Collectors.toList());
}

public ResponseEntity<?> getAllProductsByCategoryId(Long id) {
	if(id<=0) throw new EntityNotFoundException("Id can not be equal or lower than zero : " + id);
	else {
		return categoryRepository.findById(id).map(category -> {
		    List<Products> products = productRepository.findProductsByCategoriesId(id);
	        return new ResponseEntity<>(products, HttpStatus.OK);
	    }).orElseThrow(() -> new EntityNotFoundException("Product not found with id: " + id ));
	}
}


public CategoryResponseDTO getOneById(Long id) {
	if(id<=0) throw new EntityNotFoundException("Id can not be equal or lower than zero : " + id);
	else {
	Category category =  categoryRepository.findById(id)
            .orElseThrow(() -> new EntityNotFoundException("Category Not Found with id : " + id));
     return new CategoryResponseDTO(category);
	}
}


public CategoryResponseDTO create(@Valid CategoryRequestDTO newCategory) {
	Category category=new Category(newCategory);
	return new CategoryResponseDTO(categoryRepository.save(category));
}


public ResponseEntity<?> addProduct(Long id, @Valid ProductRequestDTO newProduct) {
	if(id<=0) throw new EntityNotFoundException("Id can not be equal or lower than zero : " + id);
	else {
	Products products = categoryRepository.findById(id).map(category -> {
	      long productId = newProduct.getId();
	      // extra is existed
	      if (productId >=0) {
	    	  Products _products = productRepository.findById(productId)
	            .orElseThrow(() -> new EntityNotFoundException("Not found Products with id = " + productId));
	        category.addProduct(_products);
	        categoryRepository.save(category);
	        return _products;
	      }
	      // add and create new One
	      category.addProduct(new Products(newProduct) );
	      return productRepository.save(new Products(newProduct));
	      
	    }).orElseThrow(() -> new EntityNotFoundException("Category Not found  with id = " + id));
	return new ResponseEntity<>(products, HttpStatus.CREATED);
	}
	
}


public CategoryResponseDTO update(Long id, @Valid CategoryRequestDTO newCategory) {
	if(id<=0) throw new EntityNotFoundException("Id can not be equal or lower than zero : " + id);
	else {
		 return categoryRepository.findById(id).map(category -> {
			 category.setName(newCategory.getName());
	         return new CategoryResponseDTO( categoryRepository.save(category));
	     }).orElseThrow(() -> new EntityNotFoundException("Id " + id + " not found"));	
	}

}

public ResponseEntity<?> deleteById(Long id) {
	if(id<=0) throw new EntityNotFoundException("Id can not be equal or lower than zero : " + id);
	else {
	return categoryRepository.findById(id).map(category -> {
		categoryRepository.delete(category);
        return ResponseEntity.ok().build();
    }).orElseThrow(() -> new EntityNotFoundException("Category not found with id: " + id ));
	}
}


public ResponseEntity<?> deleteProductFromCategory(Long categoryId, Long productId) {
	if(categoryId<=0 || productId <=0 ) throw new EntityNotFoundException("Ids can not be equal or lower than zero : " );
	else if(productRepository.findById(productId) ==null)
	{
		throw new EntityNotFoundException("Product Not Found with id: "+ productId);
	}
	else {
	return categoryRepository.findById(categoryId).map(category -> {
		category.removeProduct(productId);
		categoryRepository.save(category);
        return ResponseEntity.ok().build();

    }).orElseThrow(() -> new EntityNotFoundException("Category not found with id: " + categoryId ));
	}
	
}

public ResponseEntity<?> deleteAll() {
	categoryRepository.deleteAll();
	return ResponseEntity.ok().build();
}

}