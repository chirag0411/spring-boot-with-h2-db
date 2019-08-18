package com.dataone.Task2SearchFilterProducts.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.dataone.Task2SearchFilterProducts.domain.Product;

@Repository
@Transactional
public interface ProductRepository extends JpaRepository<Product, String>{
	
}