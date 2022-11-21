package com.testcase.CaseStudy.dataAccess.repositories;


import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.stereotype.Repository;

import com.testcase.CaseStudy.entites.concretes.Category;
@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {

}
