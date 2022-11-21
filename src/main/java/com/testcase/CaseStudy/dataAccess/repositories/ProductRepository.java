package com.testcase.CaseStudy.dataAccess.repositories;


import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.stereotype.Repository;

import com.testcase.CaseStudy.entites.concretes.Products;
@Repository
public interface ProductRepository extends JpaRepository<Products, Long> {

	List<Products> findProductsByCategoriesId(Long categoryId);

}
