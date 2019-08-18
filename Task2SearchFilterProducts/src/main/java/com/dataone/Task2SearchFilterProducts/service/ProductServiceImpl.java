package com.dataone.Task2SearchFilterProducts.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import com.dataone.Task2SearchFilterProducts.domain.Product;
import com.dataone.Task2SearchFilterProducts.repositories.ProductRepository;

@Service
public class ProductServiceImpl implements ProductService {

	@Autowired
	ProductRepository productRepository;

	@Override
	public Map<String, List<HashMap<String, Object>>> getFilterProductData() {

		Sort sort = new Sort(Direction.DESC, "price");
		List<Product> products = productRepository.findAll(sort);

		Map<String, List<HashMap<String, Object>>> filteredProduct = new TreeMap<String, List<HashMap<String, Object>>>();

		for (Product product : products) {
			if (filteredProduct.containsKey(product.getBrand())) {

				List<HashMap<String, Object>> productList = filteredProduct.get(product.getBrand());
				productList.add(convertReuriedHashMap(product));
				filteredProduct.put(product.getBrand(), productList);
			} else {
				List<HashMap<String, Object>> productList = new ArrayList<HashMap<String, Object>>();
				productList.add(convertReuriedHashMap(product));
				filteredProduct.put(product.getBrand(), productList);
			}
		}
		return filteredProduct;
	}

	private HashMap<String, Object> convertReuriedHashMap(Product product) {
		HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("id", product.getId());
		map.put("name", product.getName());
		map.put("price", product.getPrice());
		if (product.isOnSale()) {
			map.put("event", "ON SALE");
		}
		return map;
	}

}
