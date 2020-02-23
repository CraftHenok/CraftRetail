package com.myretailcompany.dataaccesslayer.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.myretailcompany.dataaccesslayer.entity.ProductCategory;

public interface ProductCategoryRepository extends CrudRepository<ProductCategory, Long> {

	public long count();

 
}
