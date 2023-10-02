package com.multiple.datasource.repository.product;

import org.springframework.data.jpa.repository.JpaRepository;

import com.multiple.datasource.entity.product.ProductDetails;

public interface ProductRepo extends JpaRepository<ProductDetails, Long> {
	

}
