package com.dataone.Task2SearchFilterProducts.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public interface ProductService {

	Map<String, List<HashMap<String, Object>>> getFilterProductData();

}
