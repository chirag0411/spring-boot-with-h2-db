package com.dataone.Task2SearchFilterProducts.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.dataone.Task2SearchFilterProducts.service.ProductService;

@RestController
@RequestMapping("/task2")
public class ProductSerchFilterController {

	@Autowired
	ProductService productService;

	@GetMapping("/groupByBrand")
	public ResponseEntity<?> fetchAllProduct() {

		return ResponseEntity.ok(productService.getFilterProductData());
	}
}
