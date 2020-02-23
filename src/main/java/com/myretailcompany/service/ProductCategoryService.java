package com.myretailcompany.service;

import java.util.List;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.myretailcompany.dataaccesslayer.entity.LineItem;
import com.myretailcompany.dataaccesslayer.entity.ProductCategory;
import com.myretailcompany.dataaccesslayer.repository.LineItemRepository;
import com.myretailcompany.dataaccesslayer.repository.ProductCategoryRepository;
import com.myretailcompany.rest.controller.CustomException; 

@Service
public class ProductCategoryService {

	final Logger logger = LogManager.getLogger(getClass());

	@Autowired
	private ProductCategoryRepository ProductCategoryRepo;
	
	@Autowired
	private LineItemRepository lineItemRepo;

	public ProductCategory createProductCategory(ProductCategory ProductCategoryinfo) {
		logger.info("Input recieved to create ProductCategory = " + ProductCategoryinfo);
	 	ProductCategory ProductCategory = new ProductCategory();
	 	ProductCategory.setName(ProductCategoryinfo.getName());
	 
		ProductCategory = ProductCategoryRepo.save(ProductCategory);
		logger.info("Created ProductCategory with id = " + ProductCategory.getId());
		return ProductCategory;

	}

	public void deleteProductCategory(Long id) {
	 
		ProductCategoryRepo.delete(id);
	}

	 
		


	public Iterable<ProductCategory> getAllProductCategorys() {
		Iterable<ProductCategory> ProductCategorys = ProductCategoryRepo.findAll();
		logger.info("returning all ProductCategorys");
		return ProductCategorys;
	}

	public ProductCategory getProductCategoryById(Long id) {
	 
		return ProductCategoryRepo.findOne(id);
	}

	public ProductCategory updateProductCategory(ProductCategory ProductCategoryinfo, Long id) {
	 
		ProductCategory ProductCategory = ProductCategoryRepo.findOne(id); 
		ProductCategory.setName(ProductCategoryinfo.getName());
 
		ProductCategory p = ProductCategoryRepo.save(ProductCategory); 
		return p;
	}
 
 

}
