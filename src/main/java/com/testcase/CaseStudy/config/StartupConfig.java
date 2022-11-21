package com.testcase.CaseStudy.config;


import java.util.Optional;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.testcase.CaseStudy.dataAccess.repositories.CategoryRepository;
import com.testcase.CaseStudy.dataAccess.repositories.ProductRepository;
import com.testcase.CaseStudy.entities.concretes.Category;
import com.testcase.CaseStudy.entities.concretes.Products;


@Component
public class StartupConfig implements CommandLineRunner{

	private ProductRepository productRepository;
	private CategoryRepository categoryRepository;
	
    public StartupConfig(ProductRepository productRepository,CategoryRepository categoryRepository) {
		
		this.productRepository = productRepository;
		this.categoryRepository=categoryRepository;
		
	}


	@Override
    public void run(String... args) throws Exception {
   
		Optional<Category> category =categoryRepository.findById(1L);
		 
    	if(category == null || category.isEmpty())  
		{
	
       System.out.println("Creating Datas");

       Products products1=new Products(1L,"TV","Tv-Descryption",150,null);
       Products products2=new Products(2L,"Monster Nonebook","Monster Noteboor Descryption",15000,null);
       Products products3=new Products(3L,"Chair","Chair Descryption",40,null);
       Products products4=new Products(4L,"Fork","Fork Descryption",200,null);
       Products products5=new Products(5L,"Soap","Soap Descryption",150,null);
       Products products6=new Products(6L,"Neck Pillow","Neck Pillow Descryption",500,null);
       System.out.println("..............");
       Category category1=new Category(1L,"Electronic",null);
       Category category2=new Category(2L,"Computer",null);
       Category category3=new Category(3L,"Furniture",null);
       Category category4=new Category(4L,"Home",null);
       Category category5=new Category(5L,"Cleaning",null);
       Category category6=new Category(6L,"Automobile",null);
       
       productRepository.save(products1);
       productRepository.save(products2);
       productRepository.save(products3);
       productRepository.save(products4);
       productRepository.save(products5);
       productRepository.save(products6);
       
       categoryRepository.save(category1);
       categoryRepository.save(category2);
       categoryRepository.save(category3);
       categoryRepository.save(category4);
       categoryRepository.save(category5);
       categoryRepository.save(category6);
       
       System.out.println("All datas was Created");
    
       
		}
    }
}